package ru.aston.strategy;

import ru.aston.entity;
import java.util.Comparator;
import java.util.List;

public class SortByNumberGrup implements SortStrategy {

    @Override
    public void sort(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return;
        }
        Comparator<Student> comparator = Comparator
                .comparing(Student::getGroupNumber)
                .thenComparingDouble(Student::getAverageScore)
                .thenComparing(Student::getGradeBookNumber);

        quickSort(students, comparator, 0, students.size() - 1);
    }
}