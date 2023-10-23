ThisBuild / scalaVersion := "2.13.6"

lazy val sparkSimplified = (project in file("modules/spark-simplified"))
  .settings(
    libraryDependencies ++= Dependencies.sparkExamplesDeps
  )

lazy val root = (project in file("."))
  .settings(name := "spark-simplified")
  .aggregate(
    sparkSimplified
  )
