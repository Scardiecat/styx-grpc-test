import org.scardiecat.echo.grpc.EchoServer
import org.scardiecat.echo.services.EchoService
import org.scardiecat.echo.services.PingPongService

/**
  * Created by ralfmueller on 2016-12-04.
  */
trait EchoModule  {
  lazy val echoService = new EchoService
  lazy val pongService = new PingPongService
 lazy val echoServer = EchoServer.build(echoService, pongService)
}
