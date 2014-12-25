
import re
import sys

print(sys.argv[1])
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


def build_data(programstr):
    programdata = {}
    r = re.compile(r'([a-z]+)\(([\-a-zA-Z,\(\)0-9]+)\)')
    for fact in programstr.split():
        gs = r.match(fact).groups()
        predicate = gs[0]
        args = gs[1]
        if predicate not in programdata:
            programdata[predicate] = []
        programdata[predicate].append(args)
        # programdata[predicate].append([arg for arg in args.split(",")])
    return programdata

def create_map(dim, fill=' '):
    return [[' ' for cell in range(dim)] for cell in range(dim)]

def raster(mapa,dim):
    ret = create_map(dim)
    for x in range(0,dim):
        for y in range(0,dim):
            if mapa[x][y] == ' ':
                ret[x][y] = '*'
    return ret

def debug_map(mapa, dim, start=None, end=None):
    print(start,end)
    if not start:
        start = 0
    if not end:
        end = dim
    print(start,end)
    for x in range(start,end):
        string = ""
        for y in range(start,end):
            string += str(mapa[y][x]) + " "
        print(string)

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

def map_to_file(pmap, dim, outfile):
    ofile = open(outfile, 'w')
    sdim = str(dim)
    ofile.write(sdim + " " + sdim + " ")
    for x in range(0, dim):
        for y in range(0, dim):
            if pmap[x][y] == ' ':
                ofile.write(str(0) + " ")
            else:
                ofile.write(str(1) + " ")
    ofile.close()

build_map1(parsestr)

