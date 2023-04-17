scalaVersion := "3.2.2"
name := "automorph"
organization := "example"
version := "0.0.0"

lazy val root = (project in file(".")).settings(
  name := "$name$",
  libraryDependencies ++= Seq(
    // Default
    "org.automorph" %% "automorph-default" % "0.0.1",
    "ch.qos.logback" % "logback-classic" % "1.4.6",

    // Test
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
)

