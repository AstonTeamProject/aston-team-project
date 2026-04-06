package ru.aston.entity;

import java.util.AbstractList;
import java.util.Arrays;

public class CustomStudentCollection extends AbstractList<Student> {

    private Student[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomStudentCollection() {
        this.elements = new Student[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean add(Student student) {
        if (student == null) return false;
        if (Arrays.stream(elements, 0, size).anyMatch(s -> s.getGroupNumber().equals(student.getGroupNumber()))) {
            return false;
        }
        ensureCapacity();
        elements[size++] = student;
        return true;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Student[] newElements = new Student[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Student get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public Student set(int index, Student student) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Student oldElement = elements[index];
        this.elements[index] = student;
        return oldElement;
    }

    @Override
    public String toString() {
        if (size == 0) return "Collection is empty";

        StringBuilder sb = new StringBuilder("CustomStudentCollection [\n");
        for (int i = 0; i < size; i++) {
            sb.append("  ").append(elements[i]).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
