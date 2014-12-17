
#!/usr/bin/env bash

mapstr=`clingo-3.0.5 "$@" --rand-freq=1 --asp09`
python genmaze.py "$mapstr"
