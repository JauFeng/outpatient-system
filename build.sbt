name := """outpatient-system"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

lazy val postgresVersion = "9.4.1212"

lazy val slickVersion = "3.1.1"
lazy val playSlickVersion = "2.0.2"

libraryDependencies ++= Seq(
  filters,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,

  "org.postgresql" % "postgresql" % postgresVersion,

  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.play" % "play-slick_2.11" % playSlickVersion,
  "com.typesafe.play" % "play-slick-evolutions_2.11" % playSlickVersion
)


