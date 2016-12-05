enablePlugins(GitVersioning)
enablePlugins(JavaAppPackaging)

val commonSettings = Seq(
  organization := "org.scardiecat",
  git.baseVersion := "0.0.1",
  git.gitTagToVersionNumber := { tag: String =>
    if(tag matches "[0-9]+\\..*") Some(tag)
    else None
  },
  git.useGitDescribe := true,
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:existentials", "-language:higherKinds"),


  // build info
  buildInfoPackage := "meta",
  buildInfoOptions += BuildInfoOption.ToJson,
  buildInfoOptions += BuildInfoOption.BuildTime,
  buildInfoKeys := Seq[BuildInfoKey](
    name, version, scalaVersion
  ),
  publishMavenStyle := true,
  bintrayReleaseOnPublish in ThisBuild := true,
  bintrayPackageLabels := Seq("styx", "scala", "grpc"),
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)

val commonDockerSettings = Seq(
  dockerBaseImage := "frolvlad/alpine-oraclejdk8",
  dockerExposedPorts := Seq(2551),
  maintainer in Docker := "Ralf Mueller <docker@scardiecat.org>",
  dockerRepository := Some("magicmoose-docker-registry.bintray.io/scardiecat")
)


lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin, JavaAppPackaging,DockerPlugin, GitVersioning)
  .settings(
    name := """styx-grpc-test""",
    commonSettings
  ).aggregate(app)
//
// API
//
lazy val app = (project in file("app"))
  .enablePlugins(BuildInfoPlugin, JavaAppPackaging,DockerPlugin, GitVersioning)
  .settings(
    name := "styx-grpc-test-app",
    libraryDependencies ++= Dependencies.app,
    commonSettings,
    managedSourceDirectories in Compile += (target.value / "protobuf-generated"),
    PB.targets in Compile := Seq(
      scalapb.gen(grpc = true, flatPackage = true) -> (target.value / "protobuf-generated")
    )
  )


