package ru.aston.sorting;

import ru.aston.entity.Student;
import ru.aston.sorting.comparator.StudentComparatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ParitySort extends QuickSort {
    @Override
    public void sort(List<Student> list, Comparator<Student> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GRADE_BOOK_NUMBER, SortDirection.ASC);
        List<Student> evenValues = new ArrayList<>();
        for (Student obj : list) {
            if (obj.getGradeBookNumber() % 2 == 0) {
                evenValues.add(obj);
            }
        }

        quickSort(evenValues, 0, evenValues.size() - 1, comparator);

        Iterator<Student> evenIterator = evenValues.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGradeBookNumber() % 2 == 0) {
                list.set(i, evenIterator.next());
            }
        }
    }
}
