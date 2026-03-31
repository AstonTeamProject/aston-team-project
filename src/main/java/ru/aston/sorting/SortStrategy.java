package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.CustomStudentCollection;

public interface SortStrategy {
    void sort(CustomStudentCollection collection, Comparator comparator);
}