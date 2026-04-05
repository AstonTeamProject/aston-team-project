package ru.aston.sorting.comparator;

import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import java.util.Comparator;

public class StudentAllFieldsComparator implements Comparator<Student> {
    private final SortDirection direction;

    public StudentAllFieldsComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Student s1, Student s2) {
        int result = s1.getGroupNumber().compareTo(s2.getGroupNumber());
        if (result != 0) {
            return direction == SortDirection.ASC ? result : -result;
        }

        result = Double.compare(s1.getAverageScore(), s2.getAverageScore());
        if (result != 0) {
            return direction == SortDirection.ASC ? result : -result;
        }

        result = Integer.compare(s1.getGradeBookNumber(), s2.getGradeBookNumber());
        return direction == SortDirection.ASC ? result : -result;
    }
}
