package ru.aston.entity;

import java.util.*;
import java.util.stream.*;
import java.util.Spliterator;
import java.util.Spliterators;

public class StudentShelf implements Iterable<StudentRecord> {
    private final List<StudentRecord> inner = new ArrayList<>();

    public void add(StudentRecord s) { inner.add(s); }
    public int size() { return inner.size(); }
    public StudentRecord[] toArray() { return inner.toArray(new StudentRecord[0]); }


    public Stream<StudentRecord> stream() {
        return inner.stream();
    }

    @Override
    public Iterator<StudentRecord> iterator() {
        return inner.iterator();
    }

    // Для совместимости со StreamSupport, если нужен spliterator
    @Override
    public Spliterator<StudentRecord> spliterator() {
        return Spliterators.spliterator(inner, 0);
    }
}
