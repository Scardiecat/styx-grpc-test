import sbt._

object Dependencies {

  object Version {
    val akka = "2.4.1"
    val spray = "1.3.3"
  }

  lazy val app = common

  lazy val pingpong = common

  val common = Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "io.grpc" % "grpc-netty" % "1.0.1",
    "io.grpc" % "grpc-stub" % "1.0.1",
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.5.45"
  )
}
