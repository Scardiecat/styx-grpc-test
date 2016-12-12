package org.scardiecat.styxgrpctest

/**
  * Created by ralfmueller on 2016-12-04.
  */

import org.scardiecat.styxgrpctest.EchoModule


object Server extends App with EchoModule {
   echoServer.start().awaitTermination()
}