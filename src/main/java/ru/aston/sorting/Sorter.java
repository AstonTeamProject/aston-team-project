package ru.aston.sorting;

import java.util.Comparator;
import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;

public class Sorter implements SortStrategy {

    private SortStrategy sortStrategy;

    public Sorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void sort(CustomStudentCollection collection, Comparator<Student> comparator) {
        if (sortStrategy == null) {
            throw new IllegalStateException("Sort strategy is not set");
        }
        sortStrategy.sort(collection, comparator);
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }
}