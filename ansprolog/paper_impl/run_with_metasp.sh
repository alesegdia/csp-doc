clingo-3.0.5 gem/level-core.lp gem/level-style.lp gem/level-sim.lp gem/level-shortcuts.lp --rand-freq=1 --seed=2 --reify | clingo-3.0.5 - ../metasp/meta{,D,O,C}.lp -l | clasp-3.1.1 | python ascii-render.py