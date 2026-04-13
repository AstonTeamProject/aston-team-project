package ru.aston.sorting.comparatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.sorting.QuickSort;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.comparator.StudentComparatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSortTest {
    @Test
    void testQuickSortEarlyExit() {
        QuickSort algorithm = new QuickSort();

        algorithm.sort(null, null);

        List<Student> singleElementList = new ArrayList<>();
        singleElementList.add(Student.builder().groupNumber("A").averageScore(5.0).gradeBookNumber(1).build());
        algorithm.sort(singleElementList, null);

        Assertions.assertEquals(1, singleElementList.size());
    }

    @Test
    void testQuickSortComparisonLogic() {
        QuickSort algorithm = new QuickSort();
        List<Student> students = new ArrayList<>();

        students.add(Student.builder().groupNumber("C").averageScore(5.0).gradeBookNumber(3).build()); // Больше опорного
        students.add(Student.builder().groupNumber("A").averageScore(4.0).gradeBookNumber(1).build()); // Меньше опорного
        students.add(Student.builder().groupNumber("B").averageScore(4.5).gradeBookNumber(2).build()); // Опорный (pivot)

        Comparator<Student> comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GROUP_NUMBER, SortDirection.ASC);

        algorithm.sort(students, comparator);

        Assertions.assertEquals("A", students.get(0).getGroupNumber());
        Assertions.assertEquals("B", students.get(1).getGroupNumber());
        Assertions.assertEquals("C", students.get(2).getGroupNumber());
    }

    @Test
    void testQuickSortSameIndexSwap() {
        QuickSort algorithm = new QuickSort();
        List<Student> students = new ArrayList<>();

        students.add(Student.builder().groupNumber("A").averageScore(5.0).gradeBookNumber(1).build());
        students.add(Student.builder().groupNumber("B").averageScore(6.0).gradeBookNumber(2).build());

        Comparator<Student> comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GROUP_NUMBER, SortDirection.ASC);

        algorithm.sort(students, comparator);

        Assertions.assertEquals("A", students.get(0).getGroupNumber());
    }

}
