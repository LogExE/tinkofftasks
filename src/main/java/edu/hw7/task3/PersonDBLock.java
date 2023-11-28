package edu.hw7.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// task 3.5

public class PersonDBLock implements PersonDatabase {
    HashMap<Integer, Person> persons = new HashMap<>();
    HashMap<String, Set<Integer>> byName = new HashMap<>();
    HashMap<String, Set<Integer>> byAddr = new HashMap<>();
    HashMap<String, Set<Integer>> byPhone = new HashMap<>();

    ReadWriteLock rwlock = new ReentrantReadWriteLock(true);

    @Override
    public void add(Person person) {
        int id = person.id();

        rwlock.writeLock().lock();
        try {
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
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        rwlock.writeLock().lock();
        try {
            Person toDel = persons.get(id);

            persons.remove(id);

            byName.get(toDel.name()).remove(id);

            byAddr.get(toDel.address()).remove(id);

            byPhone.get(toDel.phoneNumber()).remove(id);
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        rwlock.readLock().lock();
        try {
            return byName.get(name).stream()
                .map(persons::get)
                .toList();
        } finally {
            rwlock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        rwlock.readLock().lock();
        try {
            return byAddr.get(address).stream()
                .map(persons::get)
                .toList();
        } finally {
            rwlock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        rwlock.readLock().lock();
        try {
            return byPhone.get(phone).stream()
                .map(persons::get)
                .toList();
        } finally {
            rwlock.readLock().unlock();
        }
    }
}
