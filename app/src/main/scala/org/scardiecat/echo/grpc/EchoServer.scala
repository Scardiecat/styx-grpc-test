package org.scardiecat.echo.grpc

import io.grpc.internal.ServerImpl
import io.grpc.netty.NettyServerBuilder
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc.EchoService
import org.scardiecat.styxgrpctest.pongservice.PongServiceGrpc
import org.scardiecat.styxgrpctest.pongservice.PongServiceGrpc.PongService

import scala.concurrent.ExecutionContext

/**
  * Created by ralfmueller on 2016-12-04.
  */
object EchoServer {
  def build(echoService: EchoService, pongService: PongService): ServerImpl = {

    val echoGrpcService = EchoServiceGrpc.bindService(echoService, ExecutionContext.global)
    val pongGrpcService = PongServiceGrpc.bindService(pongService, ExecutionContext.global)
    NettyServerBuilder
      .forPort(8443)
      .addService(echoGrpcService)
      .addService(pongGrpcService)
      .build()
  }
}
