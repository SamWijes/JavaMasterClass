package ClientServer.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorServer {
	public static void main(String[] args) {
		try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
			serverChannel.bind(new InetSocketAddress(5000));
			serverChannel.configureBlocking(false);
			Selector selector=Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				selector.select();
				Set<SelectionKey> selectedKeys=selector.selectedKeys();
				Iterator<SelectionKey> iterator=selectedKeys.iterator();

				while (iterator.hasNext()){
					SelectionKey key= iterator.next();
					iterator.remove();

					if (key.isAcceptable()) {
						SocketChannel clientChannel = serverChannel.accept();
						System.out.println("Client Connected" + clientChannel.getRemoteAddress());
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						 echoData(key);
					}
				}

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void echoData(SelectionKey key) throws IOException {

		SocketChannel clientChannel= (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int byteRead = clientChannel.read(buffer); //read writes request data from the channel to the buffer instance passed

		if (byteRead > 0) {
			buffer.flip();
			byte[] data=new byte[buffer.remaining()];
			buffer.get(data);
			String message="Echo: "+ new String(data);
			clientChannel.write(ByteBuffer.wrap(message.getBytes()));
		} else if (byteRead == -1) {
			System.out.println("Client Disconnected "+clientChannel.getRemoteAddress());
			key.cancel();
			clientChannel.close();
		}


	}
}
