ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

ThisBuild / libraryDependencies ++= Seq(
  "org.typelevel" %% "weaver-core" % "0.9.3" % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "helios",
    idePackagePrefix := Some("helios")
  )

lazy val core = (project in file("core"))
  .settings(
    name := "helios-core",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.10.0",
      "org.typelevel" %% "cats-effect" % "3.5.1"
    )
  )

lazy val pictures = (project in file("renderers/pictures"))
  .settings(
    name := "picture-renderer",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.1",
      "org.typelevel" %% "cats-core" % "2.10.0"
    )
  )
  .dependsOn(core)
