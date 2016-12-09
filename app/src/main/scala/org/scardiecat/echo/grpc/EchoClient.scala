package org.scardiecat.echo.grpc

import io.grpc.ManagedChannel
import io.grpc.netty.{NegotiationType, NettyChannelBuilder}
import org.scardiecat.styxgrpctest.echoservice.EchoServiceGrpc

object EchoClient {
  def buildChannel(port:Int):ManagedChannel  = {
    NettyChannelBuilder
      .forAddress("MTL-BH157", port)
      .negotiationType(NegotiationType.PLAINTEXT)
      .build()
  }
  def buildServiceStub(channel:ManagedChannel ): EchoServiceGrpc.EchoServiceStub =  {
    EchoServiceGrpc.stub(channel)
  }
}
