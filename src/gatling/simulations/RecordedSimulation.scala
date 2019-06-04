
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8081")
    .inferHtmlResources()
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:67.0) Gecko/20100101 Firefox/67.0")

  val headers_0 = Map("Origin" -> "http://localhost:8080")


  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get("/api/parcelsize/length/5/width/5/height/5")
      .headers(headers_0))

  setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}