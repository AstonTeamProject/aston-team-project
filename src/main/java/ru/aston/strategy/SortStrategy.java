package ru.aston.strategy;

import ru.aston.entity.СustomStudentCollection;
import ru.aston.entity.Student;
import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(СustomStudentCollection collection);

    default void quickSort(СustomStudentCollection collection, Comparator<Student> comparator, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(collection, comparator, left, right);
        quickSort(collection, comparator, left, pivotIndex - 1);
        quickSort(collection, comparator, pivotIndex + 1, right);
    }

    default int partition(СustomStudentCollection collection, Comparator<Student> comparator, int left, int right) {
        Student pivot = collection.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (comparator.compare(collection.get(j), pivot) <= 0) {
                i++;
                swap(collection, i, j);
            }
        }
        swap(collection, i + 1, right);
        return i + 1;
    }

    default void swap(СustomStudentCollection collection, int i, int j) {
        if (i != j) {
            Student temp = collection.get(i);
            collection.set(i, collection.get(j));
            collection.set(j, temp);
        }
    }
}