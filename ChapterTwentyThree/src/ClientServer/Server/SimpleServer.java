package ClientServer.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			try(Socket socket=serverSocket.accept()) {

				System.out.println("Server accepts client connection");

				BufferedReader input = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));

				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String echoString=input.readLine();
					System.out.println("Server got request data: "+echoString);
					if (echoString.equals("exit")) {
						break;
					}
					output.println("echo from server: "+echoString);
				}

			}
		} catch (IOException e) {
			System.err.println("client Error: " +e.getMessage());
		}finally {
			System.out.println("Client Disconnected");
		}
	}
}
