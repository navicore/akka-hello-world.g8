$name;format="Camel"$
=======

to test:

`sbt test`

to package:

```
sbt assembly
docker build -t $name;format="Camel"$ .
```
to generate and view code coverage reports:

```
sbt clean coverage test
sbt coverageReport
open target/scala-2.12/scoverage-report/index.html
```

for code stats:

```
sbt stats
```
