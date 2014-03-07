package com.github.hexx

import unfiltered.request._
import unfiltered.response._
import unfiltered.jetty._
import unfiltered.filter._

object ACAO extends HeaderName("Access-Control-Allow-Origin")

object Json {
  def apply(json: String) = new ComposeResponse(JsonContent ~> ResponseString(json))
}

object Server extends App {
  Http(8080).filter(Planify {
    case GET(Path("/")) =>
      Ok ~> ACAO("*") ~> Json("""{"name":"Martin"}""")
    case req @ POST(Path("/")) =>
      Ok ~> ACAO("*") ~> Json(s"""{"posted": "${Body.string(req)}"}""")
  }).run()
}
