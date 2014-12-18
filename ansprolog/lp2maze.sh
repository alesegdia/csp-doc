
#!/usr/bin/env bash

clingo_exec=clingo-3.0.5

pyscript=$1
shift

seed=$1
shift

mapstr=`$clingo_exec "$@" --seed=$seed --rand-freq=1 --asp09`

#echo "$mapstr"
#python genmaze.py "$mapstr"
python $pyscript "$mapstr"
