package HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpClientGet {
	public static void main(String[] args) {
		try {
			URL url =new URL("http://localhost:8080");
			HttpClient client=HttpClient.newHttpClient();

			HttpRequest request=HttpRequest.newBuilder()
					.GET()
					.uri(url.toURI())
					.header("User-Agent","Chrome")
					.headers("Accept","application/json,text/html")
					.timeout(Duration.ofSeconds(10))
					.build();


//			HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
			HttpResponse<Stream<String>> response=client.send(request,HttpResponse.BodyHandlers.ofLines());

			if (response.statusCode()!=HTTP_OK){
				System.out.println("Error reading web page "+url);
				return;
			}

			//since string body handler was used can print out response body
//			System.out.println(response.body());
			response.body()
					.filter(s->s.contains("<h1>"))
					.map(s->s.replaceAll("<[^>]*>","").strip())
					.forEach(System.out::println);
		} catch (IOException | URISyntaxException | InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

}
