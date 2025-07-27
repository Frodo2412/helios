ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

ThisBuild / libraryDependencies ++= Seq(
  "org.typelevel" %% "weaver-cats" % "0.9.3" % Test,
  "org.typelevel" %% "weaver-scalacheck" % "0.9.3" % Test,
  "org.typelevel" %% "weaver-discipline" % "0.9.3" % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "helios",
    idePackagePrefix := Some("helios")
  ).aggregate(core, pictures)

lazy val core = (project in file("core"))
  .settings(
    name := "helios-core",
    idePackagePrefix := Some("helios"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.13.0",
      "org.typelevel" %% "cats-effect" % "3.6.3",
      "org.typelevel" %% "cats-laws" % "2.13.0" % Test,
    )
  )

lazy val pictures = (project in file("renderers/pictures"))
  .settings(
    name := "picture-renderer",
    idePackagePrefix := Some("helios"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.1",
      "org.typelevel" %% "cats-core" % "2.10.0"
    )
  )
  .dependsOn(core)
