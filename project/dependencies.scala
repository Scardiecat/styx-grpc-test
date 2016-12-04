import sbt._

object Dependencies {

  object Version {
    val akka = "2.4.1"
    val spray = "1.3.3"
  }

  lazy val app = common

  lazy val pingpong = common

  val common = Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.3"
  )

}
