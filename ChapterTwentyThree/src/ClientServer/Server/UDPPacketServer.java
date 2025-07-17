package ClientServer.Server;

import javax.management.timer.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Time;

public class UDPPacketServer {
	private static final int PORT=5000;

	private static final int PACKET_SIZE=1024;

	public static void main(String[] args) {
		try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {

			byte[] buffer = new byte[PACKET_SIZE];
			System.out.println("Waiting for a client connection");
			DatagramPacket clientPacket = new DatagramPacket(buffer, buffer.length);
			serverSocket.receive(clientPacket);
			String audioFileName=new String(buffer,0, clientPacket.getLength());
			System.out.println("client requested to listen to : " + audioFileName);

			try	{
				File audioFile=new File(audioFileName);
				AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(audioFile);
				System.out.println(audioInputStream.getFormat());
			} catch (UnsupportedAudioFileException e) {
				System.out.println(e.getMessage());
			}

			sendDataToClient(audioFileName,serverSocket,clientPacket);


		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sendDataToClient(String file,DatagramSocket serverSocket,DatagramPacket clientPacket) {

		ByteBuffer buffer=ByteBuffer.allocate(PACKET_SIZE);
		try(FileChannel fileChannel=FileChannel.open(Paths.get(file), StandardOpenOption.READ)){
			InetAddress clientIP=clientPacket.getAddress();
			int clientPort=clientPacket.getPort();

			while (true){
				buffer.clear();
				if (fileChannel.read(buffer) == -1) {
					break;
				}

				buffer.flip();
				while (buffer.hasRemaining()) {
					byte[] data=new byte[buffer.remaining()];
					buffer.get(data);
					DatagramPacket packet = new DatagramPacket(data, data.length, clientIP, clientPort);
					serverSocket.send(packet);
				}
				try {
					Thread.sleep(22);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
