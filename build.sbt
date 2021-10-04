import sbt.Keys.libraryDependencies

lazy val buildSettings = Seq(
  version := "0.2.0-SNAPSHOT",
  licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
  scalaVersion := "2.10.2",
  scalacOptions := Seq("-deprecation", "-unchecked"),
  resolvers += Resolver.sonatypeRepo("public")
)

resolvers += "maven-central" at "http://central.maven.org/maven2/"

lazy val swingDependencies = Def.setting {
  "org.scala-lang" % "scala-swing" % scalaVersion.value
}

lazy val specs2version = "2.2.2"
lazy val libDeps = Def.setting {
  "org.specs2" %% "specs2" % specs2version % "test"
}

lazy val root = (project in file(".")).
  settings(buildSettings: _*).
  settings(name := "fibonacciClock")

lazy val library = (project in file("library")).
  settings(buildSettings: _*)



lazy val swing = (project in file("swing")).
  settings(buildSettings: _*).
  settings(
    fork in run := true,
    libraryDependencies += swingDependencies.value,
    libraryDependencies += libDeps.value
  ).
  dependsOn(library)

mainClass := Some("com.fibonacciClock.swing.Main")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) =>
    xs map {
      _.toLowerCase
    } match {
      case "manifest.mf" :: Nil | "index.list" :: Nil | "dependencies" :: Nil => MergeStrategy.discard
      case _ => MergeStrategy.discard
    }
  case "conf/application.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}