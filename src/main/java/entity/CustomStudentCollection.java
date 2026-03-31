package entity;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CustomStudentCollection implements Iterable<Student> {
    private Student[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomStudentCollection() {
        this.elements = new Student[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(Student student) {
        if (student == null) return;
        for (int i = 0; i < size; i++) {
            if (elements[i].getGradeBookNumber().equals(student.getGradeBookNumber())) {
                return;
            }
        }
        ensureCapacity();
        elements[size++] = student;
    }

    public void addAllFromStream(Stream<Student> studentStream) {
        if (studentStream != null) {
            studentStream.forEach(this::add);
        }
    }

    public static CustomStudentCollection generate(int count, Supplier<Student> supplier) {
        CustomStudentCollection collection = new CustomStudentCollection();
        Stream.generate(supplier)
                .limit(count)
                .forEach(collection::add);
        return collection;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Student[] newElements = new Student[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    public int size() {
        return size;
    }

    public Student get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    public void set(int index, Student student){
        if(index < 0 || index >=size){
            throw new IndexOutOfBoundsException();
        }
        this.elements[index] = student;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public Student next() {
                if (!hasNext()) throw new NoSuchElementException();
                return elements[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        if(size == 0){
            return "Collection is empty";
        }
        StringBuilder sb = new StringBuilder("CustomStudentCollection [\n");
        for (int i = 0; i < size; i++) {
            sb.append("  ").append(elements[i]).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}