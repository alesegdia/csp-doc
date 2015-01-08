#!/usr/bin/python

# script modified from here
# http://pastie.org/2636598
# from the blog entry
# http://eis-blog.ucsc.edu/2011/10/map-generation-speedrun/  

import re
import sys

binary_term_ext = re.compile("(\w+)\((\([\d\w]+,[\d\w]+\)),([\d\w]+)\)")
binary_term = re.compile("(\w+)\(([\d\w]+),([\d\w]+)\)")
unary_term = re.compile("(\w+)\(([\d\w]+)\)")

binary_tuple = re.compile("\(([\d\w]+),([\d\w]+)\)")

TILE_TYPES_LOOKUP = { 'wall' : 'W', 'altar' : 'A', 'gem' : 'G', 'boots' : 'B', 'lava' : 'L' }

def display_maze(facts,level_map):
  """turn a list of ansprolog facts into a nice ascii-art maze diagram"""
  # NOTE: we could have took the dimensions of the map from here
  # but just for keep things simple, we are doing it in get_level_dimensions
  for fact in facts:
    # use search mode instead of match just in case we are extending the
    # asp (metaasp)
    m = binary_term_ext.search(fact)
    if m:
      functor, pos, tile_type = m.groups()
      # pos is in the format '(int,int)' but as a string
      m = binary_tuple.match( pos )
      assert m, ''.join(['Wrong binary tuple format: ',pos])
      x,y = m.groups()
      x,y = int(x),int(y) 
      if functor == 'sprite' and tile_type in TILE_TYPES_LOOKUP  :
        level_map[(x,y)] = TILE_TYPES_LOOKUP[tile_type]
 
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

def build_map(rows,columns,level_map):
  """build a two-dimensional array from the given data"""
  # default tile (walkable surface is the dot '.' char)
  level = [['.' for _ in xrange(columns)] for _ in xrange(rows)]
  for i in xrange( rows ):
    for j in xrange( columns ):
      key = (j+1,i+1)  
      if key in level_map:
        level[i][j] = level_map[key]
  return level

def main():
  """look for lines that contain logical facts and try to turn each of those
  into a maze"""
  dims = 0
  level_map = {}
  for line in sys.stdin.xreadlines():
    line = line.strip()
    if line:
      if line[0].islower():
        facts = line.split(' ')
        # We need to get the dims because the default tiles
	# the . are not listed in the model solution
        dims = max( dims, get_level_dimensions( facts ) )
        display_maze(facts,level_map)
      else:
        print "% " + line
  # let's assume the level is squared (just for the sake of simplicity)
  print 'Map dimensions: (',dims,'x',dims,')'
  print 'Map: '
  level = build_map(dims,dims,level_map)
  for row in level:
    for col in row:
      print col,
    print ''

if __name__ == "__main__":
  main()
