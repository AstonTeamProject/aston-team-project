package ru.aston.strategy;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;
import java.util.Comparator;

public class SortByNumberGrup implements SortStrategy {

    /**
     * Сортировка коллекции студентов.
     * 
     * Пример вызова в Main.java:
     * 
     * <pre>
     * CustomStudentCollection collection = new CustomStudentCollection();
     * // ... добавить студентов
     * 
     * SortStrategy sorter = new SortByNumberGrup();
     * sorter.sort(collection);
     * </pre>
     */

    @Override
    public void sort(CustomStudentCollection collection) {
        if (collection == null || collection.size() <= 1) {
            return;
        }
        Comparator<Student> comparator = Comparator
                .comparing(Student::getGroupNumber)
                .thenComparingDouble(Student::getAverageScore)
                .thenComparing(Student::getGradeBookNumber);

        quickSort(collection, comparator, 0, collection.size() - 1);
    }
}