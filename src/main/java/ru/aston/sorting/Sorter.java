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
     * @param comparator компаратор для сравнения студентов
     */
    public void sort(CustomStudentCollection collection, Comparator<Student> comparator) {
        if (sortStrategy == null) {
            throw new IllegalStateException("Sort strategy is not set");
        }
        sortStrategy.sort(collection, comparator);
    }

    /**
     * Получить текущую стратегию сортировки.
     * @return текущая стратегия
     */
    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }
}
