package dev.test;
import java.nio.charset.StandardCharsets;
import java.io.PrintStream;

public class UnicodeTest {
    public static void main(String[] args) throws Exception {
        // Set System.out to UTF-8 explicitly
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println((char) 9829); // Should print ♥
    }
}