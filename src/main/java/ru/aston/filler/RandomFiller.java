package ru.aston.filler;

import ru.aston.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;

public class RandomFiller implements DataFiller {
    private final List<String> groupNames = List.of("МАШ", "ФИТ", "ТБ", "ИКБО", "ИППО");

    @Override
    public List<Student> fill(int size) {
        return randomizeStudents(size);
    }

    public List<Student> randomizeStudents(int size) {
        int maxYear = 26;
        int minYear = 21;
        int maxSubGroup = 4;
        int minSubGroup = 1;
        
        List<String> groupNumbers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            StringBuilder groupNumber = new StringBuilder();
            groupNumber.append((int) ((Math.random() * (maxYear - minYear)) + minYear));
            groupNumber.append("-" + groupNames.get((int) ((Math.random() * (groupNames.size())))));
            int subGroup = (int) (Math.random() * maxSubGroup) + minSubGroup;
            groupNumber.append("-" + subGroup);
            groupNumbers.add(groupNumber.toString());

        }

        List<Integer> gradeBookNumbers = new ArrayList<>();
        for (int i = 1; i <= 999999; i++) {
            gradeBookNumbers.add(i);
        }
        shuffle(gradeBookNumbers);

        List<Double> averageScores = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            double randomValue = 10 * Math.random();
            randomValue = Math.round(randomValue * 100.0) / 100.0;
            averageScores.add(randomValue);
        }

        return IntStream.range(0, size)
                .mapToObj(i -> Student.builder()
                        .groupNumber(groupNumbers.get((int) (Math.random() * groupNumbers.size())))
                        .averageScore(averageScores.get(i))
                        .gradeBookNumber(gradeBookNumbers.get(i))
                        .build())
                .collect(Collectors.toList());
    }
}
