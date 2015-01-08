#!/usr/bin/env python
import sys
import argparse
'''
Create the ansprolog facts from a partial level solution in the following format:
HEIGHT
WIDTH
MAP

HEIGHT: integer
WIDTH: integer
MAP: an ascii text with HEIGHT lines x WIDTH lines length
? means uncovered
other chars see TILE_TYPE_LOOKUP
example:
5
5
? ? ? W W
. A ? ? ?
? ? ? ? W
. . . . G
? ? ? L ?
'''

TILE_TYPE_LOOKUP = {'A':'altar','B':'boots','G':'gem','L':'lava','W':'wall'}
VERBOSITY = False

def parse_partial_level(stream):
  assert stream, 'No input stream provided'
  height = int(stream.readline())
  width = int(stream.readline())
  p_level = []
  for i in xrange(height):
    line = stream.readline()
    assert line and len(line) >= (2 * width) - 1, 'Wrong input file format'
    row = []
    for j in xrange(width):
      row.append(line[2*j])
    p_level.append( row )
  return ( height, width, p_level )

def export_partial_level(height, width, partial_level, ostream):
  assert partial_level and len(partial_level) > 0 , 'Empty level!'
  assert ostream, 'No outputstream'
  for i in xrange(height):
    for j in xrange(width):
      if partial_level[i][j] != '?':
       if partial_level[i][j] in TILE_TYPE_LOOKUP:
         fact = ''.join( ['sprite','(', '(', str(j+1),',',str(i+1),')', ',', 
                        TILE_TYPE_LOOKUP[partial_level[i][j]],')','.'] )
         ostream.write( fact )
         ostream.write('\n')
         if VERBOSITY:
           print fact
       else:
         # empty cell, in our language that means a normal wlakable area
         constraint = ''.join( [ ':-', 'sprite', '(', 
                      '(', str(j+1) , ',', str(i+i), ')', ',',
                       'T', ')', '.' ] )
         ostream.write( constraint )
         ostream.write('\n')
         if VERBOSITY:
           print constraint
 
def export_dimensions_facts( height, width, ostream ):
  assert ostream, 'No output stream provided'
  # right now because we are assuming squared levels just write width
  fact = ''.join( [ 'dim','(', str(1),'..',str(width), ')','.' ] )
  ostream.write( fact )
  ostream.write('\n')
  if VERBOSITY:
    print fact

if __name__ == '__main__':
  parser = argparse.ArgumentParser()
  parser.add_argument('-f','--file', type=str, help='partial initial map')
  parser.add_argument('-gd','--generate-dims', type=str, help='generate the the constants and facts related with the map dimensions')
  parser.add_argument("-v", "--verbose", help="output verbosity",
                      action="store_true")
  parser.add_argument('-o','--output', type=str, help='out lp file with facts')
  args = parser.parse_args()
  if args.verbose:
    VERBOSITY = True

  partial_level = None
  height = 0
  width = 0
  ostream = None
  if args.file:
    f = None
    try:
      f = open(args.file,'r')
      height, width, partial_level = parse_partial_level(f)
    finally:
      if f:
        f.close()
  else:
    # if no file is provided read from standard input
    print 'No input file. Reading from the std in'
    height, width, partial_level = parse_partial_level(sys.stdin)
  if args.output:
    ostream = open( args.output, 'w' )
  else:
    ostream = open( 'a.out.lp', 'w' )
  export_partial_level(height,width,partial_level,ostream)
  if ostream:
    ostream.close()
