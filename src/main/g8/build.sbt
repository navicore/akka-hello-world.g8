name := "$name;format="Camel"$"

fork := true
javaOptions in test ++= Seq(
  "-Xms512M", "-Xmx2048M",
  "-XX:MaxPermSize=2048M",
  "-XX:+CMSClassUnloadingEnabled"
)

parallelExecution in test := false

version := "1.0"

scalaVersion := "$scalaVersion$"
val akkaVersion = "$akkaVersion$"
val akkaHttpVersion = "$akkaHttpVersion$"

libraryDependencies ++=
  Seq(
    "ch.megard" %% "akka-http-cors" % "0.3.1",

    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe" % "config" % "1.3.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",

    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

    "org.json4s" %% "json4s-native" % "3.6.1",
    "com.github.nscala-time" %% "nscala-time" % "2.20.0",

    "org.scalatest" %% "scalatest" % "3.0.5" % "test"

  )

dependencyOverrides ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

mainClass in assembly := Some("$package$.Main")
assemblyJarName in assembly := "$name;format="Camel"$.jar"

