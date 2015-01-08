#!/usr/bin/python

import re
import sys

binary_term_ext = re.compile("(\w+)\((\([\d\w]+,[\d\w]+\)),([\d\w]+)\)")
binary_term = re.compile("(\w+)\(([\d\w]+),([\d\w]+)\)")
unary_term = re.compile("(\w+)\(([\d\w]+)\)")
nary_term = re.compile('(\w+)\(([\d\w]+)([,[[\d\w]+]*)\)')

binary_tuple = re.compile("\(([\d\w]+),([\d\w]+)\)")

# concrete rules
parent_term = re.compile('parent\(([\d]+),([\d]+),([-]?[\d]+),([-]?[\d]+)\)')

def display_maze(facts,adjs):
  """Turn a list of ansprolog facts into a nice ascii-art maze diagram.
  The adjs parameter is a dictionary for storing the graph connections"""
  # NOTE: we could have took the dimensions of the map from here
  # but just for keep things simple, we are doing it in get_level_dimensions
  for fact in facts:
    m = parent_term.match(fact)
    if m:
      x, y, dx, dy = m.groups()
      # pos is in the format '(int,int)' but as a string
      x,y,dx,dy = int(x),int(y),int(dx),int(dy)
      px,py = x+dx,y+dy
      key = (px,py) 
      if key in adjs:
        adjs[key][(x,y)] = True
      else:
        # new parent found, initialize its adjacencies with the current cell
        adjs[key] = {(x,y):True} 

def get_level_dimensions(facts):
  """look for the unary predicate dim in order to get the dimensions of the maze/level."""
  max_dim = 0
  for fact in facts:
    m = unary_term.match(fact)
    if m:
      functor, dim = m.groups()
      max_dim = max(max_dim,int(dim))
  return max_dim

def build_map(rows,columns,adjs):
  """build a two-dimensional array from the given data"""
  # default tile (walkable surface is the dot '.' char)
  maze = [['()' if c % 2 == 0 else '  ' for c in xrange( ( columns )*2 - 1 )] 
          if r % 2 == 0 else ['  '] *( ( columns * 2 ) - 1 ) for r in xrange( rows * 2 - 1 )]
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
            maze[2*(cy-1)+1][2*j] = '||'
          elif cy > key[1]:
            maze[2*i+1][2*j] = '||'
          elif cx < key[0]:
            maze[2*i][2*(cx-1)+1] = '=='
          else:
            maze[2*i][2*j+1] = '=='
  return maze

def main():
  """look for lines that contain logical facts and try to turn each of those
  into a maze"""
  dims = 0
  adjacencies = {}
  for line in sys.stdin.xreadlines():
    line = line.strip()
    if line:
      if line[0].islower():
        facts = line.split(' ')
        # We need to get the dims because the default tiles
	# the . are not listed in the model solution
        dims = max( dims, get_level_dimensions( facts ) )
        display_maze(facts,adjacencies)
      else:
        print "% " + line
  # let's assume the level is squared (just for the sake of simplicity)
  print 'Map dimensions: (',dims,'x',dims,')'
  print 'Map: '
  maze = build_map(dims,dims,adjacencies)
  for row in maze:
    for col in row:
      print col,
    print ''

if __name__ == "__main__":
  main()
