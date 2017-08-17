A [g8] Template for AKKA HTTP
---

## PREREEQ

  * sbt >= 13.13
  * install giter8
    * via [g8 setup]
    * or just `brew install giter8`

## USAGE

interactively prompt for details like your project name and package name

```console
sbt new navicore/akka-hello-world.g8 
```

or oneshot via cli

```console
g8 git@github.com:navicore/akka-hello-world.g8.git --name=YOUR_PROJECT_NAME  --package=YOUR.PACKAGE.NAME
```

note, I have a problem with iterm2 and sbt interactive mode, the above works in osx terminal.  debugging...

[g8]: http://www.foundweekends.org/giter8/
[g8 setup]: http://www.foundweekends.org/giter8/setup.html 
