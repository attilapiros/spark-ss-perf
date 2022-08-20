name := "performance-spark-ss"

version := "0.1"

scalaVersion := "2.12.16"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "3.4.0-SNAPSHOT" % "provided",
  "org.apache.spark" %% "spark-hadoop-cloud" % "3.4.0-SNAPSHOT" % "provided"
)
