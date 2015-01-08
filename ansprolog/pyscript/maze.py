
import re
import sys
from util.parselp import build_data
from util.maputils import *

#print(sys.argv[1])
parsestr = str()

if len(sys.argv) < 2:
    parsestr = raw_input("Introduce cadena:\n")
else:
    parsestr = sys.argv[1]
    lines = parsestr.splitlines()
    if len(lines) > 1:
        parsestr = parsestr.splitlines()[-2]

class DimTok:
    def __init__(self, arglist):
        self.val = max(arglist)
    def __str__(self):
        return "Dimension: " + str(self.val)

class ParentTok:
    def __init__(self, args):
        gs = args.split(",")
        self.x, self.y, self.dx, self.dy = [ int(x) for x in gs ]
    def __str__(self):
        return "[" + str(self.x) + "," + str(self.y) + "," + str(self.dx) + "," + str(self.dy) + "]"



def build_map1(parsestr):
    data = build_data(parsestr)
    dim = ( int(DimTok(data['dim']).val) + 2 ) * 2
    parents = [ ParentTok(args) for args in data['parent'] ]
    mapa = create_map(dim)

    # fill centers
    for x in range(0,dim,2):
        for y in range(0,dim,2):
            mapa[x][y] = 'O'

    # apply parents
    for entry in parents:
        try:
            mapa[entry.x * 2 + entry.dx][entry.y * 2 + entry.dy] = '*'
        except Exception, e:
            pass

    rast = raster(mapa, dim)
    debug_map(mapa, dim)
    debug_map(rast, dim, start=1, end=dim-2)
    map_to_file(rast, dim, "sample.txt")


build_map1(parsestr)

