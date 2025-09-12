import sbt.ThisBuild

import scala.collection.Seq
import scala.concurrent.duration.DurationInt

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

ThisBuild / libraryDependencies ++= Seq(
  "org.typelevel" %% "weaver-cats" % "0.10.1" % Test,
  "org.typelevel" %% "weaver-scalacheck" % "0.10.1" % Test,
  "org.typelevel" %% "weaver-discipline" % "0.10.1" % Test
)

ThisBuild / strykerTimeout := 10.second
ThisBuild / strykerTimeoutFactor := 1.0

lazy val root = (project in file("."))
  .settings(
    name := "helios",
    idePackagePrefix := Some("helios")
  ).aggregate(core)

lazy val core = (project in file("core"))
  .settings(
    name := "helios-core",
    idePackagePrefix := Some("helios"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.13.0",
      "org.typelevel" %% "algebra" % "2.13.0",
      "org.typelevel" %% "cats-effect" % "3.6.3",
      "co.fs2" %% "fs2-core" % "3.12.0",
      "org.typelevel" %% "cats-laws" % "2.13.0" % Test,
      "org.typelevel" %% "algebra-laws" % "2.13.0" % Test,
    )
  )
