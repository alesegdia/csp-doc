
def create_map(dim, fill=' '):
    return [[fill for cell in range(dim)] for cell in range(dim)]

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
