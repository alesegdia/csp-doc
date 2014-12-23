
ASP LAB
=======

* ``asp/`` example logic programs taken from pcgbook.com

* ``pyscript/`` collection of python scripts for ascii visualization
of maps given a list of logic facts. They are very coupled to scripts
representation.

	* ``genmaze.py`` early and very tied to representation version of map visualizer

	* ``parfacter.py`` more generic (but still tied) visualizer, trying to focus on
	fact decomposition and interpretation.

Both scripts follow the same call format:

```python script.py "<LOGIC-PROGRAM-OUTPUT>"```

* ``lp2maze.sh`` glue to facilitate integration between ``clingo`` and
python scripts.

**You should modify the script in order to run it so that
the clingo variable points to your desired clingo executable!!**

Basic usage is:

```./lp2maze.sh <PATH-TO-PYTHON-SCRIPT> <SEED> <LIST-OF-LOGIC-SCRIPTS>```

You can also view an usage example at ``example.sh`` script.
