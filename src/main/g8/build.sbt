scalaVersion := "3.3.0"
name := "automorph"
organization := "example"
version := "0.0.1"

lazy val root = (project in file(".")).settings(
  name := "$name$",
  libraryDependencies ++= Seq(
    "org.automorph" %% "automorph-default" % "0.0.1",
    "ch.qos.logback" % "logback-classic" % "1.4.8",
  )
)

