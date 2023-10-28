package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task5Test {
    @Test
    void testAscending() {
        String[] given = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Contact[] expected = new Contact[] {
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        };
        assertArrayEquals(Task5.parseContacts(given, "ASC"), expected);
    }

    @Test
    void testDescending() {
        String[] given = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Contact[] expected = new Contact[] {
            new Contact("Carl", "Gauss"),
            new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        };
        assertArrayEquals(Task5.parseContacts(given, "DESC"), expected);
    }

    @Test
    void testEmpty() {
        String[] given = new String[] {};
        Contact[] expected = new Contact[] {};
        assertArrayEquals(Task5.parseContacts(given, "DESC"), expected);
    }

    @Test
    void testNullContacts() {
        String[] given = null;
        Contact[] expected = new Contact[] {};
        assertArrayEquals(Task5.parseContacts(given, "DESC"), expected);
    }

    @Test
    void testWithoutSurname() {
        String[] given = new String[] {"Paul", "Lena Borodak", "Danila"};
        Contact[] expected = new Contact[] {
            new Contact("Lena", "Borodak"),
            new Contact("Danila", null),
            new Contact("Paul", null)
        };
        assertArrayEquals(Task5.parseContacts(given, "ASC"), expected);
    }

    @Test
    void testOrderStrings() {
        Throwable thr = catchThrowable(() -> Task5.parseContacts(new String[] {}, "a?"));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("order");
    }

    @Test
    void testContactInfoFormat() {
        Throwable thr = catchThrowable(() -> Task5.parseContacts(new String[] {"I love trains"}, "ASC"));
        assertThat(thr).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("contact");
    }
}
