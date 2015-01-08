#!/usr/bin/env python
import sys
import argparse
import re

doc_template = '''<!DOCTYPE html>
<html>
<body>

<canvas id="myCanvas" width="{0}" height="{1}"
style="border:1px solid #000000;">
Your browser does not support the HTML5 canvas tag.
</canvas>

<script>
var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
ctx.strokeStyle = 'black';
{2}
ctx.stroke()
</script>

</body>
</html>'''

color_template = '''ctx.fillStyle = "#{hex_color}";
ctx.fillRect({x},{y},{width},{height});
ctx.rect({x},{y},{width},{height})'''

VERBOSITY = False

size_term = re.compile('size\(([\d]+),([\d]+)\)')
chunk_term = re.compile('chunk\(([\d]+),([\d]+),([\d]+)\)')
chunk_size_term = re.compile('chunk_size\(([\d]+),([\d]+),([\d]+)\)')

CHUNKS_COLORS = { 1 : 0xFF0000 }

# scale the results by a factor of 10
SCALE = 10

def get_problem_size(facts):
  for fact in facts:
    match = size_term.search( fact )
    if match:
      w, h = match.groups()
      width, height = int(w),int(h)
      size = ( width, height )
      return size
  return None

def get_chunks_sizes( facts, sizes ):
  for fact in facts:
    match = chunk_size_term.search( fact )
    if match:
      t, w, h = match.groups()
      sizes[int(t)] = {'width':int(w),'height':int(h)} 

def get_chunks(facts,chunks):
  #assert chunks, 'No chunks storagement provided'
  for fact in facts:
    match = chunk_term.search( fact )
    if match:
      x, y, t = match.groups()
      x, y, t = int(x), int(y), int(t)
      chunks[(x,y)] = { 'type' : t }

def parse_clingo_output( stream ):
  assert stream, 'No clingo output provided.'
  chunks = {}
  chunks_sizes = {}
  psize = None
  for line in stream:
    line = line.strip()
    if line:
      if line[0].islower():
        facts = line.split(' ')
        if not psize:
          psize = get_problem_size( facts )
        get_chunks_sizes( facts, chunks_sizes )
        get_chunks( facts, chunks )
      else:
        if VERBOSITY:
          print "% " + line
  # add to each chunk entry it's width and height
  for k in chunks:
    assert chunks_sizes[chunks[k]['type']], 'Unrecognized chunk type'
    size = chunks_sizes[chunks[k]['type']]
    chunks[k]['width'] = size['width']
    chunks[k]['height'] = size['height']

  assert psize, 'No problem size given'
  return psize[0], psize[1], chunks

def generate_html(height,width,chunks,ostream):
  js_text = []
  for k in chunks:
    chunk = chunks[k]
    js_text.append( color_template.format(
      hex_color=hex(CHUNKS_COLORS[chunk['type']])[2:], 
      x=k[0], y=k[1], width=chunk['width'], height=chunk['height']) )
  html = doc_template.format( width, height, '\n'.join(js_text) )
  ostream.write(html)

if __name__ == '__main__':
  parser = argparse.ArgumentParser()
  parser.add_argument('-f','--file', type=str, help='clingo knapsak output')
  parser.add_argument("-v", "--verbose", help="output verbosity",
                      action="store_true")
  parser.add_argument('-o','--output', type=str, help='out html file')
  args = parser.parse_args()
  if args.verbose:
    VERBOSITY = True

  solution = None
  height = 0
  width = 0
  ostream = None
  if args.file:
    f = None
    try:
      f = open(args.file,'r')
      width, height, solution = parse_clingo_output(f)
    finally:
      if f:
        f.close()
  else:
    # if no file is provided read from standard input
    print 'No input file. Reading from the std in'
    width, height, solution = parse_clingo_output(sys.stdin)
  if args.output:
    ostream = open( args.output, 'w' )
  else:
    ostream = open( 'a.out.html', 'w' )
  # scale up the sizes
  width = SCALE * width
  height = SCALE * height
  scaled_solution = {}
  for k in solution:
    scaled_solution[ ( k[0] * SCALE, k[1] * SCALE ) ] = { 'type' : solution[k]['type'], 
      'width' : solution[k]['width'] * SCALE , 
      'height' : solution[k]['height'] * SCALE }
    
  generate_html(height,width,scaled_solution,ostream)
  if ostream:
    ostream.close()
