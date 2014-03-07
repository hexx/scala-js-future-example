val baseSettings = Seq(
  scalaVersion := "2.10.3",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
)

lazy val server = project.settings(
  baseSettings ++ Seq(
    libraryDependencies ++= Seq(
      "net.databinder" %% "unfiltered-directives" % "0.7.1",
      "net.databinder" %% "unfiltered-filter" % "0.7.1",
      "net.databinder" %% "unfiltered-jetty" % "0.7.1"
    )
  ): _*
)

lazy val client = {
  project.settings(
    baseSettings ++ scalaJSSettings ++ Seq(
      ScalaJSKeys.loggingConsole := Option(new MyLoggerConsole(streams.value.log)),
      libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %% "scalajs-dom" % "0.2",
        "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
        "org.webjars" % "envjs" % "1.2" % "test"
      )
    ): _*
  )
}
