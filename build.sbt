import java.nio.file._


//
// Service dependencies
//
lazy val dependedProjects = SettingKey[Seq[DependedProject]]("Dependent projects and api version")

dependedProjects := Seq(
  DependedProject("pingpong","v1"),
  DependedProject("echo","v1")
)


lazy val root = (project in file("."))
  .aggregate(app)

//
// App
//
lazy val app = (project in file("app"))


//
// Tooling
//

// Helpers
def copyDir(source: Path, dest: Path) {
  Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES)
  if (source.toFile.isDirectory) {
    val dir = source.toFile
    dir.listFiles.foreach(file =>
      copyDir(file.toPath, dest.resolve(file.getName)))
  }
}

def createDir(dir:Path) {
  if (!Files.exists(dir)) {
    try {
      Files.createDirectories(dir);
    } catch {
      case e:Exception => println(e)
    }
  }
}





//
// Init Api Task
//
lazy val initApi = TaskKey[Unit]("initApi")

initApi := {
  if( ("sh deleteAPICopy.sh" !) == 0) {
  }
  println("copying api")
  println(dependedProjects.value)
  val target= "app/src/main/protobuf/"
  createDir(Paths.get(target))
  for (dep <- dependedProjects.value) {
    copyDir(Paths.get("styx-grpc-test-api", dep.projectName, dep.apiVersion), Paths.get(target, dep.projectName ))
  }
}


// For reference
//lazy val buildFrontend = taskKey[Unit]("Execute frontend scripts")
//
//buildFrontend := {
//  val s: TaskStreams = streams.value
//  val shell: Seq[String] = if (sys.props("os.name").contains("Windows")) Seq("cmd", "/c") else Seq("bash", "-c")
//  val npmInstall: Seq[String] = shell :+ "npm install"
//  val npmTest: Seq[String] = shell :+    "npm run test"
//  val npmLint: Seq[String] = shell :+    "npm run lint"
//  val npmBuild: Seq[String] = shell :+   "npm run build"
//  s.log.info("building frontend...")
//  if((npmInstall #&& npmTest #&& npmLint #&& npmBuild !) == 0) {
//    s.log.success("frontend build successful!")
//  } else {
//    throw new IllegalStateException("frontend build failed!")
//  }
//}

//Tests

concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)
