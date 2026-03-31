package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;

public interface SortStrategy {
    void sort(CustomStudentCollection collection, Comparator<Student> comparator);
}