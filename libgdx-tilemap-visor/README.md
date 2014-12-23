
libgdx-tilevisor
===============

To use the LibGDX visualizer, you first need to build **tilevisor**. There is an utility
script ``build-tilevisor.sh`` for that at its folder, but will probably only work with
Unix like environments because of the gradlew script it uses. You can probably workaround
that for your operating system. More info at https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline.

To execute it:

```java -jar <PATH-TO-TILEVISOR-JAR> [<MAP-FILE>]```

As specified, ``<MAP-FILE>`` is optional. In case you don't specify it, a map is generated.


