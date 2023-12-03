package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuotesServer {
    private QuotesServer() {

    }

    private final static Logger LOGGER = LogManager.getLogger();
    private final static int CLIENTS_CAPACITY = 6;
    private final static int PORT = 7777;
    private final static Map<String, List<String>> QUOTES = Map.of(
        "личности", List.of(
            "Не переходи на личности там, где их нет"
        ),
        "оскорбления", List.of(
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "Люди опускаются до оскорблений по скользким ступеням ненависти, держась за грязные поручни зависти"
        ),
        "глупый", List.of(
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        ),
        "интеллект", List.of(
            "Чем ниже интеллект, тем громче оскорбления",
            "Не нужно критиковать мой интеллект… Ведь если он начнёт падать, то придавит все ваши амбиции"
        )
    );

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        try (ExecutorService ex = Executors.newFixedThreadPool(CLIENTS_CAPACITY)) {
            try (ServerSocket sock = new ServerSocket(PORT)) {
                while (true) {
                    Socket client = sock.accept();
                    LOGGER.info("+ " + client.getInetAddress());
                    ex.execute(new QuoteDispenserThread(client));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    static class QuoteDispenserThread extends Thread {
        private final ThreadLocalRandom rand = ThreadLocalRandom.current();
        private final Socket client;

        QuoteDispenserThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
                while (true) {
                    String query = in.readLine();
                    LOGGER.info(client.getInetAddress() + " -> " + query);
                    String response;
                    if (query == null) {
                        LOGGER.info("- " + client.getInetAddress());
                        break;
                    } else if (!QUOTES.containsKey(query)) {
                        String keywords = QUOTES.keySet().stream().collect(Collectors.joining("\", \"", "\"", "\""));
                        response =
                            "Не знаю как ответить на твой запрос... Доступные для запросов ключевые слова: " + keywords;
                    } else {
                        List<String> choices = QUOTES.get(query);
                        response = choices.get(rand.nextInt(choices.size()));
                    }
                    out.write(response + "\n");
                    LOGGER.info(response + " -> " + client.getInetAddress());
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
