package sortingTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.sorting.QuickSort;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.Sorter;
import ru.aston.sorting.comparator.StudentComparatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SorterTest {
    @Test
    void testNullSortStrategy(){
        Sorter sorter = new Sorter(null);
        Student student = Student.builder().groupNumber("12").gradeBookNumber(12).averageScore(5.1).build();
        List<Student> list = new ArrayList<>();
        list.add(student);
        Comparator<Student> comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GRADE_BOOK_NUMBER, SortDirection.ASC);
        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class, () -> sorter.sort(list, comparator)
        );
        Assertions.assertEquals(exception.getMessage(), "Sort strategy is not set");
    }

    @Test
    void testSortStrategy(){
        QuickSort quickSort = new QuickSort();
        Sorter sorter = new Sorter(quickSort);
        Assertions.assertEquals(quickSort, sorter.getSortStrategy());
        QuickSort newStrategy = new QuickSort();
        sorter.setStrategy(newStrategy);
        Assertions.assertEquals(newStrategy, sorter.getSortStrategy());
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().groupNumber("B").averageScore(5.0).gradeBookNumber(2).build());
        students.add(Student.builder().groupNumber("A").averageScore(4.0).gradeBookNumber(1).build());
        Comparator<Student> comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GROUP_NUMBER, SortDirection.ASC);
        sorter.sort(students, comparator);
        Assertions.assertEquals("A", students.getFirst().getGroupNumber());
    }
}
