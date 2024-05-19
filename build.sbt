ThisBuild / scalaVersion := "2.13.6"

lazy val sparkExamples = (project in file("modules/spark-examples"))
  .settings(
    libraryDependencies ++= Dependencies.sparkExamplesDeps
  )

lazy val sparkSimplified = (project in file("modules/spark-simplified"))
  .settings(
    libraryDependencies ++= Dependencies.sparkSimplifiedDeps
  )

lazy val parquetHadoop = (project in file("modules/parquet-hadoop"))
  .settings(
    libraryDependencies ++= Dependencies.sparkSimplifiedDeps
  )

lazy val root = (project in file("."))
  .settings(name := "spark-simplified")
  .aggregate(
    parquetHadoop,
    sparkSimplified,
    sparkExamples
  )
