package ru.aston.sorting.comparatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.comparator.StudentGradeBookNumberComparator;

public class StudentGradeBookNumberComparatorTest {
    @Test
    void compareTest() {
        StudentGradeBookNumberComparator comparator = new StudentGradeBookNumberComparator(SortDirection.ASC);
        Student s1 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(12).build();
        Student s2 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s1, s2) < 0);
    }

    @Test
    void compareDescTest() {
        StudentGradeBookNumberComparator comparator = new StudentGradeBookNumberComparator(SortDirection.DESC);
        Student s1 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(12).build();
        Student s2 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s1, s2) > 0);
    }
}
