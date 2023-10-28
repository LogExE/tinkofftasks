package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task4 {
    private Task4() {

    }

    private static final ArrayList<String> romanBases = new ArrayList<>(List.of(
        "I",
        "X",
        "C",
        "M"
    ));

    private static final ArrayList<String> romanFives = new ArrayList<>(List.of(
        "V",
        "L",
        "D"
    ));

    static final int MIN_ROMAN = 1;

    static final int MAX_ROMAN = 3999;

    // https://www.quora.com/How-do-I-write-a-program-that-converts-Arabic-numerals-to-Roman-numerals-and-vice-versa
    @SuppressWarnings({"ParameterAssignment", "MagicNumber"})
    public static String convertToRoman(int num) {
        if (num < MIN_ROMAN || num > MAX_ROMAN) {
            throw new IllegalArgumentException("Can't build roman representation for this number!");
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < romanBases.size(); ++i) {
            if (num == 0) {
                break;
            }
            int cnt = num % 10;
            String base = romanBases.get(i);
            // здесь в значениях case везде порядок обратный тому, что в алгоритме с quora
            // поскольку число мы разбираем с младшего разряда
            String toAppend = switch (cnt) {
                case 1, 2, 3 -> base.repeat(cnt);
                case 4 -> romanFives.get(i) + base;
                case 5 -> romanFives.get(i);
                case 6, 7, 8 -> base.repeat(cnt - 5) + romanFives.get(i);
                case 9 -> romanBases.get(i + 1) + base;
                default -> "";
            };
            sb.append(toAppend);
            num /= 10;
        }

        // возвращаем нормальный порядок
        return sb.reverse().toString();
    }
}
