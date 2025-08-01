package HttpProject;

import HttpProject.handlers.JsonBodyHandler;
import HttpProject.handlers.ThreadSafeFileHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChallConcurrentRequest {

	private static final Lock lock=new ReentrantLock();
	private static final Path orderTracing = Path.of("orderTracking.json");
	public static void main(String[] args) {
		Map<String,Integer> orderMap =
				Map.of( "apples", 500,
						"oranges", 1000,
						"bananas", 750,
						"carrots", 2000,
						"cantaloupes", 100 );

		String urlparams="product=%s&amount=%d";
		String urlBase="http://localhost:8080";

		List<URI> sites=new ArrayList<>();

		orderMap.forEach((k,v)->sites.add(URI.create(urlBase+"?"+urlparams.formatted(k,v))));

		HttpClient client=HttpClient.newHttpClient();

//		sendGets(client,sites);

		if (!Files.exists(orderTracing)) {
			try {
				Files.createFile(orderTracing);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

//		sendPostsFileHandler(client,urlBase,urlparams,orderMap);
		sendPostsGetJSON(client,urlBase,urlparams,orderMap);
	}

	public static void writeToFile(String content) {
		lock.lock();
		try {
			Files.writeString(orderTracing, content +"\r", StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally {
			lock.unlock();
		}
	}

	private static void sendGets(HttpClient client, List<URI> uris) {

		var futures=uris.stream()
				.map(uri-> HttpRequest.newBuilder(uri))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

		futures.forEach(f->{
			System.out.println(f.join().body());
		});

	}




	private static void sendPosts(HttpClient client, String baseURI,String paramString ,Map<String ,Integer> orders) {

		var futures=orders.entrySet().stream()
				.map(e->paramString.formatted(e.getKey(),e.getValue()))
				.map(s-> HttpRequest.newBuilder(URI.create(baseURI))
						.POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
		List<String > lines=new ArrayList<>();
		futures.forEach(f->{
			lines.add(f.join().body());
		});

		try {
			Files.write(orderTracing,lines, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static void sendPostsGetJSON(HttpClient client, String baseURI,String paramString ,Map<String ,Integer> orders) {
		ObjectMapper objectMapper=new ObjectMapper();
		var handler = JsonBodyHandler.create(objectMapper);
		var futures=orders.entrySet().stream()
				.map(e->paramString.formatted(e.getKey(),e.getValue()))
				.map(s-> HttpRequest.newBuilder(URI.create(baseURI))
						.POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request, handler))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

		futures.forEach(f->{
			JsonNode node = f.join().body().get("order"); //getting order node which has data
			System.out.printf("Order Id: %s Expected Delivery:%s %n",
					node.get("orderId"),node.get("orderDeliveryDate").asText());
		});

	}

	private static void sendPostsWithFileResponse(HttpClient client, String baseURI,String paramString ,Map<String ,Integer> orders) {

		var futures=orders.entrySet().stream()
				.map(e->paramString.formatted(e.getKey(),e.getValue()))
				.map(s-> HttpRequest.newBuilder(URI.create(baseURI))
						.POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(orderTracing,
						StandardOpenOption.APPEND)))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

	}

	private static void sendPostsFileHandler(HttpClient client, String baseURI,String paramString ,Map<String ,Integer> orders) {
	var handler=new ThreadSafeFileHandler(orderTracing);
		var futures=orders.entrySet().stream()
				.map(e->paramString.formatted(e.getKey(),e.getValue()))
				.map(s-> HttpRequest.newBuilder(URI.create(baseURI))
						.POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request,handler))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

	}
	private static void sendPostsSafeFileWrite(HttpClient client, String baseURI,String paramString ,Map<String ,Integer> orders) {

		var futures=orders.entrySet().stream()
				.map(e->paramString.formatted(e.getKey(),e.getValue()))
				.map(s-> HttpRequest.newBuilder(URI.create(baseURI))
						.POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request->client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
						.thenAcceptAsync(r->writeToFile(r.body())))
				.toList();

		var allFutureRequests= CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();

	}
}
