package ru.aston.sorting.comparator;

import entity.Student;
import ru.aston.sorting.SortDirection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class StudentComparatorFactory {

    public enum Field {
        GROUP_NUMBER,
        AVERAGE_SCORE,
        GRADE_BOOK_NUMBER,
        ALL
    }

    private static final Map<Field, Function<SortDirection, Comparator<Student>>> COMPARATORS =
        Map.of(
            Field.GROUP_NUMBER, StudentGroupNumberComparator::new,
            Field.AVERAGE_SCORE, StudentAverageScoreComparator::new,
            Field.GRADE_BOOK_NUMBER, StudentGradeBookNumberComparator::new,
            Field.ALL, StudentAllFieldsComparator::new
        );

    public static Comparator<Student> create(Field field, SortDirection direction) {
        Function<SortDirection, Comparator<Student>> factory = COMPARATORS.get(field);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown field: " + field);
        }
        return factory.apply(direction);
    }
}
