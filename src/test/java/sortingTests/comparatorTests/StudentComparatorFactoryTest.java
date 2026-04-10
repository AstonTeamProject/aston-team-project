package sortingTests.comparatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.comparator.*;

import java.util.Comparator;

public class StudentComparatorFactoryTest {
    @Test
    void testFactoryCreateCorrectComparators() {
        Comparator<Student> comparator = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.AVERAGE_SCORE, SortDirection.ASC
        );
        Assertions.assertTrue(comparator instanceof StudentAverageScoreComparator);

        Comparator<Student> comparator1 = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GROUP_NUMBER, SortDirection.ASC
        );
        Assertions.assertTrue(comparator1 instanceof StudentGroupNumberComparator);

        Comparator<Student> comparator2 = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.GRADE_BOOK_NUMBER, SortDirection.ASC
        );
        Assertions.assertTrue(comparator2 instanceof StudentGradeBookNumberComparator);

        Comparator<Student> comparator3 = StudentComparatorFactory.create(
                StudentComparatorFactory.Field.ALL, SortDirection.ASC
        );
        Assertions.assertTrue(comparator3 instanceof StudentAllFieldsComparator);
    }


}
