package edu.hw3.task5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Task5 {
    private Task5() {

    }

    private static final String ASCENDING_ORDER_ARG = "ASC";
    private static final String DESCENDING_ORDER_ARG = "DESC";

    public static Contact[] parseContacts(String[] people, String order) {
        if (!order.equals(ASCENDING_ORDER_ARG) && !order.equals(DESCENDING_ORDER_ARG)) {
            throw new IllegalArgumentException("Invalid sorting order!");
        }
        int sz = people != null ? people.length : 0;
        Contact[] res = new Contact[sz];
        for (int i = 0; i < sz; ++i) {
            String[] split = people[i].split(" ");
            Contact newCont = switch (split.length) {
                case 1 -> new Contact(split[0], null);
                case 2 -> new Contact(split[0], split[1]);
                default -> throw new IllegalArgumentException("At least one of contacts was specified incorrectly!");
            };
            res[i] = newCont;
        }

        Comparator<Contact> comp =
            order.equals(ASCENDING_ORDER_ARG) ? Comparable::compareTo : Collections.reverseOrder();
        Arrays.sort(res, comp);

        return res;
    }
}
