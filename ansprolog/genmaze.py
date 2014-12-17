
import re

def create_map(dim, fill=' '):
    return [[' ' for x in range(dim)] for x in range(dim)]

def_mapa = raw_input("Introduce la cadena de definicion del mapa:\n")

tokens = def_mapa.split()
some_parent = False

r = re.compile(r'dim\((\d)\)')
i = 0

dim = 0
for p in tokens:
    m = r.match(p)
    if not m:
        break
    else:
        i = i + 1
        dim = int(m.groups()[0])

dim = (dim + 2) * 2
tokens = tokens[i:]
mapa = create_map(dim)

for x in range(0,dim,2):
    for y in range(0,dim,2):
        mapa[x][y] = 'O'

r = re.compile(r'parent\((-?\d),(-?\d),(-?\d),(-?\d)\)')

for p in tokens:
    print(p)
    a = r.match(p).groups()
    m = [ int(x) for x in a ]
    mapa[ m[0] * 2 + m[2] ] [ m[1]*2 + m[3]] = '*'

for x in range(1,dim-2):
    string = ""
    for y in range(1,dim-2):
        string += str(mapa[x][y]) + " "
    print(string)

def raster(amap):
    ret = [[' ' for x in range(dim)] for x in range(dim)]
    for x in range(0,dim):
        for y in range(0,dim):
            if mapa[x][y] == ' ':
                ret[x][y] = '*'
    return ret


rast = raster(mapa)
for x in range(1,dim-2):
    string = ""
    for y in range(1,dim-2):
        string += str(rast[x][y]) + " "
    print(string)


