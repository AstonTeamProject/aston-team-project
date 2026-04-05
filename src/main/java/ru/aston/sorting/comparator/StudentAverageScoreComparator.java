package ru.aston.sorting.comparator;

import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import java.util.Comparator;

public class StudentAverageScoreComparator implements Comparator<Student> {
    private final SortDirection direction;

    public StudentAverageScoreComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Student s1, Student s2) {
        int result = Double.compare(s1.getAverageScore(), s2.getAverageScore());
        return direction == SortDirection.ASC ? result : -result;
    }
}
