package ru.aston.filler;
import ru.aston.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.Collections.shuffle;

public class RandomFiller implements DataFiller{
    private final List<String> groupNames = Arrays.asList("МАШ", "ФИТ", "ТБ", "ИКБО", "ИППО");

    @Override
    public List<Student> fill(int size) {
        return randomizeStudents(size);
    }

    public List<Student> randomizeStudents(int size){

        List<String> groupNumbers = new ArrayList<>();
        for (int i = 1; i<=size; i++) {
            String groupNumber = "";
            groupNumber += String.valueOf((int)((Math.random() * (26 - 21)) + 21));
            groupNumber += "-" + groupNames.get((int)((Math.random() * (groupNames.size()))));
            int subGroup = (int)(Math.random() * 4) + 1;
            groupNumber += "-" + subGroup;
            groupNumbers.add(groupNumber);

        }

        List<Integer> gradeBookNumbers = new ArrayList<>();
        for (int i = 100000; i<=999999; i++){
            gradeBookNumbers.add(i);
        }
        shuffle(gradeBookNumbers);

        List<Double> averageScores = new ArrayList<>();
        for (int i = 1; i<=size; i++){
            Random r = new Random();
            double randomValue = 10 * r.nextDouble();
            randomValue = Math.round(randomValue * 100d) / 100d;
            averageScores.add(randomValue);
        }

        return IntStream.range(0, size)
                .mapToObj(i -> Student.builder()
                        .groupNumber(groupNumbers.get((int)(Math.random() * groupNumbers.size())))
                        .averageScore(averageScores.get(i))
                        .gradeBookNumber(gradeBookNumbers.get(i))
                        .build())
                .collect(Collectors.toList());
    }
}
