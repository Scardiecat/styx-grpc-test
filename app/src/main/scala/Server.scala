/**
  * Created by ralfmueller on 2016-12-04.
  */

import io.grpc.ServerInterceptors
import io.grpc.internal.ServerImpl
import io.grpc.netty.NettyServerBuilder
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc.EchoService

import scala.concurrent.ExecutionContext

object Server extends App with EchoModule {
   echoServer.start().awaitTermination()
}