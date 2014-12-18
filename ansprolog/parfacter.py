
import re
import sys

parsestr = str()
if len(sys.argv) < 2:
    parsestr = raw_input("Introduce cadena:\n")
else:
    parsestr = sys.argv[1]

def build_data(programstr):
    programdata = {}
    r = re.compile(r'(parent|dim)\(((-?\d+,)*-?\d+|-?\d+)\)')
    for fact in programstr.split():
        gs = r.match(fact).groups()
        predicate = gs[0]
        args = gs[1]
        if predicate not in programdata:
            programdata[predicate] = []
        programdata[predicate].append([arg for arg in args.split(",")])
    return programdata

print(build_data(parsestr))

