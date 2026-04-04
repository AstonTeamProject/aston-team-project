package ru.aston.readFromFile;

import ru.aston.entity.Student;
import java.util.*;
import java.util.stream.*;

public class StudentShelf implements Iterable<Student> {
    private final List<Student> inner = new ArrayList<>();

    public void add(Student s) { inner.add(s); }
    public int size() { return inner.size(); }
    public Student[] toArray() { return inner.toArray(new Student[0]); }

    public Stream<Student> stream() {
        return inner.stream();
    }

    @Override
    public Iterator<Student> iterator() {
        return inner.iterator();
    }
}
