package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDBLock;
import edu.hw7.task3.PersonDBSync;
import edu.hw7.task3.PersonDatabase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Task3Test {
    static Arguments[] dummyPars() {
        return new Arguments[15];
    }

    @ParameterizedTest
    @MethodSource("dummyPars")
    void testSync() {
        PersonDatabase db = new PersonDBSync();

        List<Person> t1expected = List.of(new Person(0, "Agafya", "Pushkina, 39", "+79506410323"));
        List<Person> t2expected = List.of(new Person(1, "Fedya", "Lermontova, 11", "+79443229982"));
        List<Person> t3expected = List.of(new Person(2, "Vladimir", "Memnaya, 42", "+79531112002"));

        Thread t1 = new Thread(() -> {
            db.add(new Person(0, "Agafya", "Pushkina, 39", "+79506410323"));
            assertEquals(t1expected, db.findByName("Agafya"));
            assertEquals(t1expected, db.findByAddress("Pushkina, 39"));
            assertEquals(t1expected, db.findByPhone("+79506410323"));
        });

        Thread t2 = new Thread(() -> {
            db.add(new Person(1, "Fedya", "Lermontova, 11", "+79443229982"));
            assertEquals(t2expected, db.findByName("Fedya"));
            assertEquals(t2expected, db.findByAddress("Lermontova, 11"));
            assertEquals(t2expected, db.findByPhone("+79443229982"));
        });

        Thread t3 = new Thread(() -> {
            db.add(new Person(2, "Vladimir", "Memnaya, 42", "+79531112002"));
            assertEquals(t3expected, db.findByName("Vladimir"));
            assertEquals(t3expected, db.findByAddress("Memnaya, 42"));
            assertEquals(t3expected, db.findByPhone("+79531112002"));
        });

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            fail("InterruptedException occured, what the...?");
        }
    }

    @ParameterizedTest
    @MethodSource("dummyPars")
    void testRWLocks() {
        PersonDatabase db = new PersonDBLock();

        List<Person> t1expected = List.of(new Person(0, "Agafya", "Pushkina, 39", "+79506410323"));
        List<Person> t2expected = List.of(new Person(1, "Fedya", "Lermontova, 11", "+79443229982"));
        List<Person> t3expected = List.of(new Person(2, "Vladimir", "Memnaya, 42", "+79531112002"));

        Thread t1 = new Thread(() -> {
            db.add(new Person(0, "Agafya", "Pushkina, 39", "+79506410323"));
            assertEquals(t1expected, db.findByName("Agafya"));
            assertEquals(t1expected, db.findByAddress("Pushkina, 39"));
            assertEquals(t1expected, db.findByPhone("+79506410323"));
        });

        Thread t2 = new Thread(() -> {
            db.add(new Person(1, "Fedya", "Lermontova, 11", "+79443229982"));
            assertEquals(t2expected, db.findByName("Fedya"));
            assertEquals(t2expected, db.findByAddress("Lermontova, 11"));
            assertEquals(t2expected, db.findByPhone("+79443229982"));
        });

        Thread t3 = new Thread(() -> {
            db.add(new Person(2, "Vladimir", "Memnaya, 42", "+79531112002"));
            assertEquals(t3expected, db.findByName("Vladimir"));
            assertEquals(t3expected, db.findByAddress("Memnaya, 42"));
            assertEquals(t3expected, db.findByPhone("+79531112002"));
        });

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            fail("InterruptedException occured, what the...?");
        }
    }
}
