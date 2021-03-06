#!/usr/bin/env python

import re
import sys

binary_term = re.compile("(\w+)\(([\d\w]+),([\d\w]+)\)")
unary_term = re.compile("(\w+)\(([\d\w]+)\)")
nary_term = re.compile('(\w+)\(([\d\w]+)([,[[\d\w]+]*)\)')

binary_tuple = re.compile("\(([\d\w]+),([\d\w]+)\)")

# concrete rules
cell_term = re.compile('cell\(([\w]+),([\d]+),([\d]+)\)')
passable_term = re.compile('passable\(([\d]+),([\d]+),([\d]+),([\d]+)\)')

COLORS_LOOKUP = {'red':'R', 'yellow':'Y', 'green':'G', 'cyan':'C', 
		'blue':'B', 'magenta':'M'}

def build_maze_data(facts,adjs,colors):
  """Turn a list of ansprolog facts into maze info.
  The adjs parameter is a dictionary for storing the graph connections
  colors is another dictionary with each cell's color"""
  # NOTE: we could have took the dimensions of the map from here
  # but just for keep things simple, we are doing it in get_level_dimensions
  for fact in facts:
    m = passable_term.search(fact)
    if m:
      x1, y1, x2, y2 = m.groups()
      x1,y1,x2,y2 = int(x1),int(y1),int(x2),int(y2)
      key = (x1,y1) 
      if key in adjs:
        adjs[key][(x2,y2)] = True
      else:
        adjs[key] = {(x2,y2):True} 
    else:
        # colors
        m = cell_term.search(fact)
        if m:
          color, x, y = m.groups()
          x,y = int(x), int(y)
          assert (color in COLORS_LOOKUP), 'Unrecognized color' 
          colors[(x,y)] = COLORS_LOOKUP[color]

def get_level_dimensions(facts):
  """look for the unary predicate dim in order to get the dimensions of the maze/level."""
  max_dim = 0
  for fact in facts:
    m = unary_term.search(fact)
    if m:
      functor, dim = m.groups()
      if functor == 'dim':
        max_dim = max(max_dim,int(dim))
  return max_dim

def build_maze(rows,columns,adjs,colors):
  """build a two-dimensional array from the given data"""
  # default tile (walkable surface is the dot '.' char)
  maze = [[colors[(c//2 + 1, r//2 + 1)] if c % 2 == 0 else ' ' for c in xrange( ( columns )*2 - 1 )] 
          if r % 2 == 0 else [' '] *( ( columns * 2 ) - 1 ) for r in xrange( rows * 2 - 1 )]
  # the maze has spaces between "rooms" in order to set the connections
  for i in xrange( rows ):
    for j in xrange( columns ):
      key = (j+1,i+1)  
      #level[2*i][2*j] = '()'
      if key in adjs:
        # this node has children
        children = adjs[key]
	for child in children:
          cx,cy = child
	  # || for children that are above or below
          if cy < key[1]:
            # get the correct connection row
            # the one between the current and the upper or lower
            maze[2*(cy-1)+1][2*j] = '|'
          elif cy > key[1]:
            maze[2*i+1][2*j] = '|'
          elif cx < key[0]:
            maze[2*i][2*(cx-1)+1] = '='
          else:
            maze[2*i][2*j+1] = '='
  return maze

def main():
  """look for lines that contain logical facts and try to turn each of those
  into a maze"""
  dims = 0
  adjacencies = {}
  colors = {}
  for line in sys.stdin.xreadlines():
    line = line.strip()
    if line:
      if line[0].islower():
        facts = line.split(' ')
        # We need to get the dims because the default tiles
	# the . are not listed in the model solution
        dims = max( dims, get_level_dimensions( facts ) )
        build_maze_data(facts,adjacencies,colors)
      else:
        print "% " + line
  # let's assume the level is squared (just for the sake of simplicity)
  print 'Map dimensions: (',dims,'x',dims,')'
  print 'Map: '
  maze = build_maze(dims,dims,adjacencies,colors)
  for row in maze:
    for col in row:
      print col,
    print ''

if __name__ == "__main__":
  main()
