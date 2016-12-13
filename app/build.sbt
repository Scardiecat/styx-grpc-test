enablePlugins(GitVersioning)
enablePlugins(JavaAppPackaging)
enablePlugins(BuildInfoPlugin)
enablePlugins(GitVersioning)
enablePlugins(DockerPlugin)


organization := "org.scardiecat"
git.baseVersion := "0.0.1"
git.gitTagToVersionNumber := { tag: String =>
  if(tag matches "[0-9]+\\..*") Some(tag)
    else None
}
git.useGitDescribe := true
scalaVersion := "2.11.8"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:existentials", "-language:higherKinds")


// build info
buildInfoPackage := "meta"
buildInfoOptions += BuildInfoOption.ToJson
buildInfoOptions += BuildInfoOption.BuildTime
buildInfoKeys := Seq[BuildInfoKey](
  name, version, scalaVersion
)

publishMavenStyle := true
bintrayReleaseOnPublish in ThisBuild := true
bintrayPackageLabels := Seq("styx", "scala", "grpc")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))



dockerBaseImage := "anapsix/alpine-java:8_jdk_unlimited"
dockerExposedPorts := Seq(2551)
maintainer in Docker := "Ralf Mueller <docker@scardiecat.org>"
dockerRepository := Some("magicmoose-docker-registry.bintray.io/scardiecat")
dockerExposedPorts := Seq(8443)


name := """styx-grpc-test-app"""
//
// API
//
libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "io.grpc" % "grpc-netty" % "1.0.1",
    "io.grpc" % "grpc-stub" % "1.0.1",
    "org.javassist" % "javassist" % "3.21.0-GA",
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.5.45"
  )
  
managedSourceDirectories in Compile += (target.value / "protobuf-generated")

PB.targets in Compile := Seq(
  scalapb.gen(grpc = true, flatPackage = true) -> (target.value / "protobuf-generated")
)

mainClass in (Compile)  := Some("org.scardiecat.styxgrpctest.Server")


// Tests
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"