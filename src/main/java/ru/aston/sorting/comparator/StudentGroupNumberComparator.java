package ru.aston.sorting.comparator;

import entity.Student;
import ru.aston.sorting.SortDirection;
import java.util.Comparator;

public class StudentGroupNumberComparator implements Comparator<Student> {
    private final SortDirection direction;

    public StudentGroupNumberComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Student s1, Student s2) {
        int result = s1.getGroupNumber().compareTo(s2.getGroupNumber());
        return direction == SortDirection.ASC ? result : -result;
    }
}
