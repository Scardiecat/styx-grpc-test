import java.util.concurrent.{CountDownLatch, TimeUnit}

import org.scardiecat.echo.grpc.EchoClient
import org.scardiecat.styxgrpctest.echoservice.{Message, SendMessageRequest}

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object Client extends App{
  def time(f: => Unit)={
    val s = System.currentTimeMillis
    f
    System.currentTimeMillis - s
  }

  var count = 500
  val latch:CountDownLatch = new CountDownLatch(count)
  val channel = EchoClient.buildChannel(8443)
  val echoServiceStub = EchoClient.buildServiceStub(channel)

  var x = 0
  var outp = 0
 println (time {
   for (x <- 1 to count) {
     if (x - outp > 20000) {
       Thread.sleep(1)
     }
     val sendMessage = echoServiceStub
       .sendEcho(SendMessageRequest("hello"))
     sendMessage.onComplete {
       case Success(value: Message) => {
         //println(s"Message received: $value")
         outp = outp + 1
         latch.countDown()
         if (outp % 50000 == 0) {
           println(s"$outp")
         }
       }
       case Failure(e) => {
         e.printStackTrace
         latch.countDown()
       }
     }
   }
   println("sent")
   latch.await(5, TimeUnit.MINUTES)
 })
  channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  println("shutdown")
}