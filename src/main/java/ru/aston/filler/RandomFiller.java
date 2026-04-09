package ru.aston.filler;

import ru.aston.entity.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {
    private final List<String> groupNames = List.of("МАШ", "ФИТ", "ТБ", "ИКБО", "ИППО");

    @Override
    public List<Student> fill(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    return randomizeStudent();
                })
                .collect(Collectors.toList());
    }

    private Student randomizeStudent() {
        return Student.builder()
                .groupNumber(randomizeGroupNumber())
                .averageScore(randomizeAverageScore())
                .gradeBookNumber(randomizeGradeBookNumber())
                .build();
    }

    private String randomizeGroupNumber() {
        int maxYear = 26;
        int minYear = 21;
        int maxSubGroup = 4;
        int minSubGroup = 1;

        StringBuilder groupNumber = new StringBuilder();
        groupNumber.append((int) ((Math.random() * ((maxYear + 1) - minYear)) + minYear));
        groupNumber.append("-" + groupNames.get((int) ((Math.random() * (groupNames.size())))));
        int subGroup = (int) (Math.random() * maxSubGroup) + minSubGroup;
        groupNumber.append("-" + subGroup);
        return groupNumber.toString();
    }

    private double randomizeAverageScore() {
        double averageScore = 10 * Math.random();
        averageScore = Math.round(averageScore * 100.0) / 100.0;
        return averageScore;
    }

    private Integer randomizeGradeBookNumber() {
        return (int) (Math.random() * 999999);
    }
}
