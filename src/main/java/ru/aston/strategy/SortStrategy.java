package ru.aston.strategy;

import ru.aston.entity.Student;
import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(List<Student> students);

    default void quickSort(List<Student> list, Comparator<Student> comparator, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(list, comparator, left, right);
        quickSort(list, comparator, left, pivotIndex - 1);
        quickSort(list, comparator, pivotIndex + 1, right);
    }

    default int partition(List<Student> list, Comparator<Student> comparator, int left, int right) {
        Student pivot = list.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, right);
        return i + 1;
    }

    default void swap(List<Student> list, int i, int j) {
        if (i != j) {
            Student temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}