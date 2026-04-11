package ru.aston.filler;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {
    private static final int MAX_YEAR = 26;
    private static final int MIN_YEAR = 21;
    private static final int MAX_SUB_GROUP = 4;
    private static final int MIN_SUB_GROUP = 1;
    private static final int MAX_GRADE_BOOK_NUMBER = 999999;
    private static final List<String> GROUP_NAMES = List.of("МАШ", "ФИТ", "ТБ", "ИКБО", "ИППО");

    @Override
    public List<Student> fill(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> randomizeStudent())
                .collect(Collectors.toCollection(CustomStudentCollection::new));
    }

    private Student randomizeStudent() {
        return Student.builder()
                .groupNumber(randomizeGroupNumber())
                .averageScore(randomizeAverageScore())
                .gradeBookNumber(randomizeGradeBookNumber())
                .build();
    }

    private String randomizeGroupNumber() {
        StringBuilder groupNumber = new StringBuilder();
        groupNumber.append((int) ((Math.random() * ((MAX_YEAR + 1) - MIN_YEAR)) + MIN_YEAR));
        groupNumber.append("-").append(GROUP_NAMES.get((int) ((Math.random() * (GROUP_NAMES.size())))));
        int subGroup = (int) (Math.random() * MAX_SUB_GROUP) + MIN_SUB_GROUP;
        groupNumber.append("-").append(subGroup);
        return groupNumber.toString();
    }

    private double randomizeAverageScore() {
        double averageScore = 10 * Math.random();
        averageScore = Math.round(averageScore * 100.0) / 100.0;
        return averageScore;
    }

    private Integer randomizeGradeBookNumber() {
        return (int) (Math.random() * MAX_GRADE_BOOK_NUMBER);
    }
}