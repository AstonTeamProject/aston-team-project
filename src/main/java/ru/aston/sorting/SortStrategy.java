package ru.aston.sorting;

import java.util.Comparator;
import java.util.List;
import ru.aston.entity.Student;

public interface SortStrategy {
    void sort(List<Student> list, Comparator<Student> comparator);
}