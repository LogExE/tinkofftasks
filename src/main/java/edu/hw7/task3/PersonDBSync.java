package edu.hw7.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonDBSync implements PersonDatabase {
    HashMap<Integer, Person> persons = new HashMap<>();
    HashMap<String, Set<Integer>> byName = new HashMap<>();
    HashMap<String, Set<Integer>> byAddr = new HashMap<>();
    HashMap<String, Set<Integer>> byPhone = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        int id = person.id();

        persons.put(id, person);

        if (!byName.containsKey(person.name())) {
            byName.put(person.name(), new HashSet<>());
        }
        byName.get(person.name()).add(id);

        if (!byAddr.containsKey(person.address())) {
            byAddr.put(person.address(), new HashSet<>());
        }
        byAddr.get(person.address()).add(id);

        if (!byPhone.containsKey(person.phoneNumber())) {
            byPhone.put(person.phoneNumber(), new HashSet<>());
        }
        byPhone.get(person.phoneNumber()).add(id);
    }

    @Override
    public synchronized void delete(int id) {
        Person toDel = persons.get(id);

        persons.remove(id);

        byName.get(toDel.name()).remove(id);

        byAddr.get(toDel.address()).remove(id);

        byPhone.get(toDel.phoneNumber()).remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return byName.get(name).stream()
            .map(persons::get)
            .toList();
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return byAddr.get(address).stream()
            .map(persons::get)
            .toList();
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return byPhone.get(phone).stream()
            .map(persons::get)
            .toList();
    }
}
