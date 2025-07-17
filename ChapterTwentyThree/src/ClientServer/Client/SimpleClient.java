package ClientServer.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 5000)) {
			System.out.println("Server accepts client connection");
			BufferedReader input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			Scanner scanner = new Scanner(System.in);
			String requestString;
			String responseString;

			do {
				System.out.println("Enter String to be echoed(Sent to server)");
				requestString = scanner.nextLine();
				output.println(requestString);
				if (!requestString.equals("exit")) {
					responseString = input.readLine();
					System.out.println(responseString);
				}
			} while (!requestString.equals("exit"));

		} catch (IOException e) {
			System.err.println("client Error: " + e.getMessage());
		} finally {
			System.out.println("Client Disconnected");
		}

	}
}
