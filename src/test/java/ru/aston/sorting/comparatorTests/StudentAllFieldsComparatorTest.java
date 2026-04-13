package ru.aston.sorting.comparatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.comparator.StudentAllFieldsComparator;

public class StudentAllFieldsComparatorTest {

    @Test
    void compareByAllFields(){
        StudentAllFieldsComparator comparator = new StudentAllFieldsComparator(SortDirection.ASC);
        Student s1 = Student.builder().groupNumber("A").averageScore(5.1).gradeBookNumber(12).build();
        Student s2 = Student.builder().groupNumber("B").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s1,s2) < 0);
        Student s3 = Student.builder().groupNumber("A").averageScore(5.1).gradeBookNumber(12).build();
        Student s4 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s3,s4) < 0);
        Student s5 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(12).build();
        Student s6 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s5,s6) < 0);
    }

    @Test
    void compareByAllFieldsDesc(){
        StudentAllFieldsComparator comparator = new StudentAllFieldsComparator(SortDirection.DESC);
        Student s1 = Student.builder().groupNumber("A").averageScore(5.1).gradeBookNumber(12).build();
        Student s2 = Student.builder().groupNumber("B").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s1,s2) > 0);
        Student s3 = Student.builder().groupNumber("A").averageScore(5.1).gradeBookNumber(12).build();
        Student s4 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s3,s4) > 0);
        Student s5 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(12).build();
        Student s6 = Student.builder().groupNumber("A").averageScore(5.2).gradeBookNumber(13).build();
        Assertions.assertTrue(comparator.compare(s5,s6) > 0);
    }
}
