scalaVersion := "3.3.0"
name := "automorph"
organization := "example"

lazy val root = (project in file(".")).settings(
  name := "$name$",
  libraryDependencies ++= Seq(
    "org.automorph" %% "automorph-default" % "0.0.0-SNAPSHOT",
    "ch.qos.logback" % "logback-classic" % "1.4.8",
  )
)

