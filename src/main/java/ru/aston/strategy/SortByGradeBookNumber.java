package ru.aston.strategy;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;
import java.util.Comparator;

public class SortByGradeBookNumber implements SortStrategy {
    @Override
    public void sort(CustomStudentCollection collection) {
        if (collection == null || collection.size() <= 1) {
            return;
        }
        Comparator<Student> comparator = Comparator
                .comparing(Student::getGradeBookNumber)
                .thenComparing(Student::getGroupNumber)
                .thenComparingDouble(Student::getAverageScore);

        quickSort(collection, comparator, 0, collection.size() - 1);
    }
}
