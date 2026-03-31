package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.Student;
import ru.aston.entity.CustomStudentCollection;

public class QuickSort implements SortStrategy{
    public void quickSort(CustomStudentCollection collection, int left, int right, Comparator<Student> comparator) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(collection, left, right, comparator);
        quickSort(collection, left, pivotIndex - 1, comparator);
        quickSort(collection, pivotIndex + 1, right, comparator);
    }

    private int partition(CustomStudentCollection collection, int left, int right, Comparator<Student> comparator) {
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

    private void swap(CustomStudentCollection collection, int i, int j) {
        if (i != j) {
            Student temp = collection.get(i);
            collection.set(i, collection.get(j));
            collection.set(j, temp);
        }
    }

    @Override
    public void sort(CustomStudentCollection collection, Comparator<Student> comparator) {
        if (collection == null || collection.size() <= 1) {
            return;
        }
        quickSort(collection, 0, collection.size() - 1, comparator);
    }
}