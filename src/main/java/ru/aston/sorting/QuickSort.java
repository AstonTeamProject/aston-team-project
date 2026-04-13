package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.Student;
import java.util.List;

public class QuickSort implements SortStrategy{
    public void quickSort(List<Student> list, int left, int right, Comparator<Student> comparator) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(list, left, right, comparator);
        quickSort(list, left, pivotIndex - 1, comparator);
        quickSort(list, pivotIndex + 1, right, comparator);
    }

    private int partition(List<Student> list, int left, int right, Comparator<Student> comparator) {
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

    private void swap(List<Student> list, int i, int j) {
        if (i != j) {
            Student temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    @Override
    public void sort(List<Student> list, Comparator<Student> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }
}