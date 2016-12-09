import java.nio.file._

lazy val root = (project in file("."))
  .aggregate(app)
//
// API
//
lazy val app = (project in file("app"))


lazy val copyRes = TaskKey[Unit]("copyRes")

def copyDir(source: Path, dest: Path) {
  Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES)
  if (source.toFile.isDirectory) {
    val dir = source.toFile
    dir.listFiles.foreach(file =>
      copyDir(file.toPath, dest.resolve(file.getName)))
  }
}

copyRes := {
  val list =
  copyDir(Paths.get("styx-grpc-test-api"),Paths.get("styx-grpc-test-api2"))
}


