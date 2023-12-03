package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class QuotesClient {
    private QuotesClient() {

    }

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 7777;

    @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        try (BufferedReader conIn = new BufferedReader(new InputStreamReader(System.in));
             Socket sock = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {
            while (true) {
                System.out.print("> ");
                String query = conIn.readLine();
                if (query == null) {
                    break;
                }
                out.write(query + "\n");
                out.flush();
                System.out.println(in.readLine());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
