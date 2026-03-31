package ru.aston.strategy;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;
import java.util.Comparator;

public class SortByAverageScore implements SortStrategy {
    @Override
    public void sort(CustomStudentCollection collection) {
        if (collection == null || collection.size() <= 1) {
            return;
        }
        Comparator<Student> comparator = Comparator
                .comparingDouble(Student::getAverageScore)
                .thenComparing(Student::getGroupNumber)
                .thenComparing(Student::getGradeBookNumber);

        quickSort(collection, comparator, 0, collection.size() - 1);
    }
}
