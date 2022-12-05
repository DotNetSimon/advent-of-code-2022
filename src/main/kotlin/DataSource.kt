import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val urlPrefix = "https://adventofcode.com/2022/day/"
private const val urlSuffix = "/input"
private const val cookieHeaderKey = "cookie"

// set this in your env settings based on the cookie from the website, so you can get your personal data.
private const val cookieEnvVariable = "AOC2022-cookie"

class DataSource {
    companion object {
        private val cookie = System.getenv(cookieEnvVariable)

        fun getDataAsList(day: Int): List<String> {
            val url = "$urlPrefix$day$urlSuffix"
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(cookieHeaderKey, cookie)
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            return response.body().split("\n").filterNot { it == "" }
        }
    }
}
