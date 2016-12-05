

lazy val root = (project in file("."))
  .aggregate(app)
//
// API
//
lazy val app = (project in file("app"))



