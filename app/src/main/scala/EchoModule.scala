import org.scardiecat.echo.grpc.EchoServer
import org.scardiecat.echo.services.EchoService

/**
  * Created by ralfmueller on 2016-12-04.
  */
trait EchoModule  {
  lazy val echoService = new EchoService
 lazy val echoServer = EchoServer.build(echoService)
}
