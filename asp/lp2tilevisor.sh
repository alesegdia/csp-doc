
#!/usr/bin/env bash

./lp2maze.sh $@
java -jar ../libgdx-tilemap-visor/tilevisor-desktop.jar sample.txt
rm sample.txt

