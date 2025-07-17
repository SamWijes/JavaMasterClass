package HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExample {
	public static void main(String[] args) {
		try {
//			URL url = new URL("http://example.com/extra");
			URL url =new URL("http://localhost:8080");
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			//set user agent
			connection.setRequestProperty("User-Agent","Chrome");
			connection.setRequestProperty("Accept","application/json,text/html");
			connection.setReadTimeout(10000);
			int responseCode= connection.getResponseCode(); //performs the connection implicitly
			System.out.printf("Response code: %d%n",responseCode);
			if (responseCode!=HTTP_OK){
				System.out.println("Error reading web Page "+url);
				System.out.printf("Error %s%n",connection.getResponseMessage());
				return;
			}
			printContents(connection.getInputStream()); //also implicitly connects
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private static void printContents(InputStream is) {
		try(BufferedReader inputStream=new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = inputStream.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
