
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

class TileTok:
    next_identifier = 0
    identifier_map = dict()

    @staticmethod
    def getIdentifier(identifier):
        if identifier in TileTok.identifier_map:
            return TileTok.identifier_map[identifier]
        else:
            TileTok.identifier_map[identifier] = TileTok.next_identifier
            TileTok.next_identifier = TileTok.next_identifier + 1
            return TileTok.identifier_map[identifier]

    def __init__(self, args):
        gs = args.split(",")
        gs, self.figtype = gs[1:], gs[0]
        self.identifier, self.x, self.y = [ int(x)-1 for x in gs ]
        self.identifier = str(self.identifier) + self.figtype
        self.identifier = TileTok.getIdentifier(self.identifier)

    def __str__(self):
        return "[" + str(self.identifier) + ": " + str(self.x) + "," + str(self.y) + "]"


def build_map1(parsestr):
    data = build_data(parsestr)
    dim = int(DimTok(data['dim']).val)
    tiles = [ TileTok(args) for args in data['tile'] ]
    mapa = create_map(dim, fill='.')

    # apply parents
    for entry in tiles:
        try:
            mapa[entry.x][entry.y] = entry.identifier
        except Exception, e:
            pass

    debug_map(mapa, dim)
    map_to_file(mapa, dim, "sample.txt")


build_map1(parsestr)

