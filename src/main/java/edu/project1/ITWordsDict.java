package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class ITWordsDict implements WordsDict {
    private static final String[] IT_WORDS = {"laptop", "java", "coding", "plugin", "coffee"};
    private final Random rand = new Random();

    @Override
    @NotNull
    public String randomWord() {
        int index = rand.nextInt(IT_WORDS.length);
        return IT_WORDS[index];
    }
}
