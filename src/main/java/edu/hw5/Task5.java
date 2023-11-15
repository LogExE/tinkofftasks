package edu.hw5;

import org.intellij.lang.annotations.RegExp;

public class Task5 {
    private Task5() {

    }

    // https://auto.ru/mag/article/avtomobilnye-kody-regionov-rossii
    @RegExp
    private static final String REG_RU =
        "2[1-9]|[013-9]\\d|92|95|[17]02|[17]16|121|93|1[29]3|12[456]|13[468]|14[27]"
            + "|90|[17][59]0|15[2469]|[17]6[13]|164|1?96|17[34]|9[79]|177|19[79]|777|79[79]|98|1[79]8|186|94";
    @RegExp
    private static final String LETTERS_RU = "[АВЕКМНОРСТУХ]";
    private static final String RUS_CAR = String.format("%s\\d{3}%s{2}(%s)", LETTERS_RU, LETTERS_RU, REG_RU);

    public static boolean isValidRuCar(String str) {
        return str.matches(RUS_CAR);
    }
}
