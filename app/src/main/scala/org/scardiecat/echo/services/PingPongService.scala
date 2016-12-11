package org.scardiecat.echo.services

import java.util.UUID

import org.scardiecat.styxgrpctest.pongservice.{PongMessage, PongServiceGrpc, SendPingMessageRequest}

import scala.concurrent.Future

/**
  * Created by ralfmueller on 2016-12-10.
  */
class PingPongService extends PongServiceGrpc.PongService{
  override def sendPing(request: SendPingMessageRequest): Future[PongMessage] = {
    Future.successful(PongMessage(UUID.randomUUID().toString, "1", request.content))
  }
}
