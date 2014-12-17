
#!/usr/bin/env bash

seed=$1
shift
mapstr=`clingo-3.0.5 "$@" --seed=$seed --rand-freq=1 --asp09`
#echo "$mapstr"
python genmaze.py \""$mapstr"\"
