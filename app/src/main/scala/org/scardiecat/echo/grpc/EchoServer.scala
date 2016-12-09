package org.scardiecat.echo.grpc

import io.grpc.internal.ServerImpl
import io.grpc.netty.NettyServerBuilder
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc.EchoService

import scala.concurrent.ExecutionContext

/**
  * Created by ralfmueller on 2016-12-04.
  */
object EchoServer {
  def build(echoService: EchoService): ServerImpl = {

    val echoGrpcService = EchoServiceGrpc.bindService(echoService, ExecutionContext.global)

    NettyServerBuilder
      .forPort(8443)
      .addService(echoGrpcService)
      .build()
  }
}
