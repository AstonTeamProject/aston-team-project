package ru.aston.sorting;

import java.util.Comparator;
import java.util.List;
import ru.aston.entity.Student;

public class Sorter {

    private SortStrategy sortStrategy;

    public Sorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void sort(List<Student> list, Comparator<Student> comparator) {
        if (sortStrategy == null) {
            throw new IllegalStateException("Sort strategy is not set");
        }
        sortStrategy.sort(list, comparator);
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }
}