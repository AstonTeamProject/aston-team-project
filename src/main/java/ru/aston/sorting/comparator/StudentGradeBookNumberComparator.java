package ru.aston.sorting.comparator;

import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import java.util.Comparator;

public class StudentGradeBookNumberComparator implements Comparator<Student> {
    private final SortDirection direction;

    public StudentGradeBookNumberComparator(SortDirection direction) {
        this.direction = direction;
    }

    @Override
    public int compare(Student s1, Student s2) {
        int result = Integer.compare(s1.getGradeBookNumber(), s2.getGradeBookNumber());
        return direction == SortDirection.ASC ? result : -result;
    }
}
