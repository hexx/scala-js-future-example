import scala.scalajs.js
import scala.scalajs.test.JasmineTest

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import com.github.hexx.Ajax

import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object HtmldaJsJasmineTest extends JasmineTest {
  describe("Futures and Promises") {
    it("should work on Scala.js.") {
      val json = for {
        res1 <- Ajax.get("http://localhost:8080/")
        json = js.JSON.parse(res1.responseText)
        res2 <- Ajax.post("http://localhost:8080/", s"${json.name}-san")
      } yield js.JSON.parse(res2.responseText)

      val posted = Await.result(json, Duration.Zero).posted

      expect(posted).toEqual("Martin-san")
    }
  }
}
