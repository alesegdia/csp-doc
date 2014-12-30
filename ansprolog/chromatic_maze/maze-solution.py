#!/usr/bin/env python

import re
import sys

binary_term = re.compile("(\w+)\(([\d\w]+),([\d\w]+)\)")
unary_term = re.compile("(\w+)\(([\d\w]+)\)")
nary_term = re.compile('(\w+)\(([\d\w]+)([,[[\d\w]+]*)\)')

binary_tuple = re.compile("\(([\d\w]+),([\d\w]+)\)")

# concrete rules
grid_size_term = re.compile('tile_grid\(([\d]+),([\d]+)\)')
tile_char_term = re.compile('tile_char\(([\d]+),([\d]+),([\d\w]+)\)')
passable_term = re.compile('passable\(([\d]+),([\d]+),([\d]+),([\d]+)\)')

def build_maze_data(facts,adjs,dists):
  """Turn a list of ansprolog facts into concrete info for the maze.
  The adjs parameter is a dictionary for storing the graph connections
  The dists are the last digit of the path distances"""
  # NOTE: we could have took the dimensions of the map from here
  # but just for keep things simple, we are doing it in get_level_dimensions
  for fact in facts:
    m = passable_term.search(fact)
    if m:
      x1, y1, x2, y2 = m.groups()
      # pos is in the format '(int,int)' but as a string
      x1, y1, x2, y2 = int(x1),int(y1),int(x2),int(y2)
      key = (x1,y1) 
      if key in adjs:
        adjs[key][(x2,y2)] = True
      else:
        # new parent found, initialize its adjacencies with the current cell
        adjs[key] = {(x2,y2):True} 
    else:
        # distance 
        m = tile_char_term.search(fact)
        if m:
          x, y, d = m.groups()
          x, y = int(x), int(y)
          if d == 's' or d == 'f':
            dists[(x,y)] = d
          else:
            # because we don't want to make a mess with the maze
            # make sure all the ascii cells has the same size
            dists[(x,y)] = str( ( int(d) % 10 ) )

def get_level_dimensions(facts):
  """look for the tile_grid predicate in order to get the dimensions of the maze/level."""
  dims = None
  for fact in facts:
    m = grid_size_term.search(fact)
    if m:
      width, height = m.groups()
      dims = (int(width),int(height))
      break
  return dims

def build_path(rows,columns,adjs,dists):
  """build a two-dimensional array from the given data"""
  maze = [['-' if c % 2 == 0 else ' ' for c in xrange( ( columns )*2 - 1 )]
  if r % 2 == 0 else [' '] *( ( columns * 2 ) - 1 ) for r in xrange( rows * 2 - 1 )]
  # the maze has spaces between "rooms" in order to set the connections
  for i in xrange( rows ):
    for j in xrange( columns ):
      key = (j+1,i+1)  
      if key in dists:
        maze[2*i][2*j] = dists[key]
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
  dims = None
  adjacencies = {}
  distances = {}
  for line in sys.stdin.xreadlines():
    line = line.strip()
    if line:
      if line[0].islower():
        facts = line.split(' ')
        # We need to get the maze dimensions
        # there's only one fact tile_grid(size,size)
        if not dims:
          dims = get_level_dimensions( facts )
        build_maze_data(facts,adjacencies,distances)
      else:
        print "% " + line
  # let's assume the level is squared (just for the sake of simplicity)
  print 'Map dimensions: (',dims[0],'x',dims[1],')'
  print 'Map: '
  maze = build_path(dims[1],dims[0],adjacencies,distances)
  for row in maze:
    for col in row:
      print col,
    print ''

if __name__ == "__main__":
  main()
