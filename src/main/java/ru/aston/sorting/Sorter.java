package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;

/**
 * Контекст для выбора стратегии сортировки.
 * Позволяет менять стратегию сортировки динамически.
 */
public class Sorter {

    private SortStrategy sortStrategy;
    private String sortByField;

    public Sorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    /**
     * Установить стратегию сортировки.
     * @param sortStrategy стратегия сортировки
     */
    public void setStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    /**
     * Выполнить сортировку коллекции.
     * @param collection коллекция студентов
     */
    public void sort(CustomStudentCollection collection) {
        if (sortStrategy == null) {
            throw new IllegalStateException("Sort strategy is not set");
        }
        Comparator<Student> comparator = createComparator();
        sortStrategy.sort(collection, comparator);
    }

    /**
     * Создать компаратор на основе поля сортировки.
     * @return компаратор для сравнения студентов
     */
    private Comparator<Student> createComparator() {
        if (sortByField == null || sortByField.isEmpty()) {
            return Comparator.comparing(Student::getGroupNumber);
        }

        switch (sortByField) {
            case "groupNumber":
                return Comparator.comparing(Student::getGroupNumber);
            case "averageScore":
                return Comparator.comparingDouble(Student::getAverageScore);
            case "gradeBookNumber":
                return Comparator.comparing(Student::getGradeBookNumber);
            default:
                return Comparator.comparing(Student::getGroupNumber);
        }
    }

    /**
     * Установить поле для сортировки.
     * @param sortByField название поля: "groupNumber", "averageScore", "gradeBookNumber"
     */
    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    /**
     * Получить текущую стратегию сортировки.
     * @return текущая стратегия
     */
    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    /**
     * Получить поле, по которому выполняется сортировка.
     * @return название поля
     */
    public String getSortByField() {
        return sortByField;
    }
}
