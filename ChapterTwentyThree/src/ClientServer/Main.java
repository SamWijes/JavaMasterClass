package ClientServer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class Main {
	public static void main(String[] args) {

		Consumer<ByteBuffer> printBuffer = (buffer) -> {
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			System.out.printf("\"%s\" ", new String(data, StandardCharsets.UTF_8));
		};
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		doOperation("Print: ",buffer,(b)-> System.out.print(b+" "));
		doOperation("Write: ",buffer,b->b.put("This is a text".getBytes()));
		doOperation("Flip(From Write to Read): ",buffer, ByteBuffer::flip);
		doOperation("Read and Print value",buffer,printBuffer);

		doOperation("Flip(From Read to Write): ",buffer, ByteBuffer::flip);
//		doOperation("1. Move position to end of the text",buffer,b->b.position(b.limit()));
//		doOperation("2. Change limit to capacity",buffer,b->b.limit(b.capacity()));

		doOperation("Compact",buffer,ByteBuffer::compact);
		doOperation("Append: ",buffer,b->b.put("This is a new Text".getBytes()));
//		doOperation("Flip(From Write to Read): ",buffer, ByteBuffer::flip);
		doOperation("Read and Print value",buffer.slice(0,buffer.position()),printBuffer);
	}

	private static void doOperation(String op, ByteBuffer buffer, Consumer<ByteBuffer> c) {

		System.out.printf("%-30s", op);
		c.accept(buffer);
		System.out.printf("Capacity=%d, Limit=%d, Position=%d, Remaining=%d%n",
				buffer.capacity(),
				buffer.limit(),
				buffer.position(),
				buffer.remaining());
	}

}

