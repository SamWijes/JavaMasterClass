package ClientServer.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DatagramChannelServer {
	private static final int PORT=5000;
	private static final int PACKET_SIZE=1024;
	public static void main(String[] args) {

		try (DatagramChannel channel = DatagramChannel.open()) {

			channel.bind(new InetSocketAddress(PORT));
			System.out.println("Server is listening to the port" + PORT);
			Selector selector=Selector.open();
			channel.configureBlocking(false);
			channel.register(selector,SelectionKey.OP_READ);
			ByteBuffer buffer=ByteBuffer.allocate(PACKET_SIZE);

			while (true) {
				selector.select();
				Set<SelectionKey> selectedKeys=selector.selectedKeys();
				Iterator<SelectionKey> iterator=selectedKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key=iterator.next();
					iterator.remove();

					if (key.isReadable()) {
						DatagramChannel registeredChannel=(DatagramChannel) key.channel();
						buffer.clear();
						SocketAddress clientAddress=registeredChannel.receive(buffer);
						buffer.flip();
						byte[] data = new byte[buffer.remaining()];
						buffer.get(data);
						String audioFilePath = new String(data);
						System.out.println("Client Requested to listen to:"+audioFilePath);

						new Thread(()->sendDataToClient(audioFilePath,clientAddress,registeredChannel)).start();
					}

				}
			}
		}  catch (IOException e) {
			throw new RuntimeException(e);
		}

	}


	private static void sendDataToClient(String file, SocketAddress address, DatagramChannel channel) {
		ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
		try (FileChannel fileChannel = FileChannel.open(Paths.get(file), StandardOpenOption.READ)) {

			while (true){
				buffer.clear();
				int byteRead = fileChannel.read(buffer);
				if (byteRead == -1) {
					break;
				}
				buffer.flip();
				while (buffer.hasRemaining()) {
					channel.send(buffer, address);

				}

				try {
					TimeUnit.MILLISECONDS.sleep(22);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
