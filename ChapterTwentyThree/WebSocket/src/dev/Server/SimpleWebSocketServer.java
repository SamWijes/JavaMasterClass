package dev.Server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleWebSocketServer extends WebSocketServer {

	private static final int SERVER_PORT=8080;

	private static Map<String, String> map = new HashMap<>();


	public SimpleWebSocketServer() {
		super(new InetSocketAddress(SERVER_PORT));
	}

	public static void main(String[] args) {
		var server=new SimpleWebSocketServer();
		server.start();
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		var resource=webSocket.getResourceDescriptor();//data about request ->/?name=Sam
		String name=resource.split("=")[1];
		map.put(webSocket.getRemoteSocketAddress().toString(), name);
		System.out.println(map.values());
		System.out.println("Connection was opened"+webSocket.getRemoteSocketAddress());
		broadcastAllButSender(webSocket,"%s joined".formatted(name));
	}

	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b) {
		System.out.println("Connection was Closed"+webSocket.getRemoteSocketAddress());
	}

	@Override
	public void onMessage(WebSocket webSocket, String s) {
//		System.out.println("Message Received"+webSocket.getRemoteSocketAddress());
		String chatName = map.get(webSocket.getRemoteSocketAddress().toString());
		broadcastAllButSender(webSocket,"%s : %s".formatted(chatName,s));
	}

	private void broadcastAllButSender(WebSocket webSocket, String message) {
		var connections=new ArrayList<>(getConnections());
		connections.remove(webSocket);//removing the current connection->senders connection
		broadcast(message,connections);
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {
		System.out.println("Error for "+webSocket.getRemoteSocketAddress());
	}

	@Override
	public void onStart() {
		System.out.println("Server is listenin on the port :"+getPort());
	}
}
