package ru.aston.sorting;

import ru.aston.entity.Student;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ParitySort extends QuickSort {
    @Override
    public void sort(List<Student> list, Comparator<Student> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        List<Student> evenValues;
        evenValues = list.stream().filter(i -> i.getGradeBookNumber() % 2 == 0).collect(Collectors.toList());
        for (Student obj : list) {
            if (obj.getGradeBookNumber() % 2 == 0) {
                evenValues.add(obj);
            }
        }
        if (evenValues.size() > 0) {
            quickSort(evenValues, 0, evenValues.size() - 1, comparator);
            Iterator<Student> evenIterator = evenValues.iterator();
            for (int i = 0; i < list.size() && evenIterator.hasNext(); i++) {
                if (list.get(i).getGradeBookNumber() % 2 == 0) {
                    list.set(i, evenIterator.next());
                }
            }
        }
    }
}
