package ru.aston.filler;

import ru.aston.entity.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentFileReader {
    private static final double minScore = 0.0;
    private static final double maxScore = 10.0;

    public StudentFileReader() { }

    public List<Student> readAll(Path filePath, int size) throws IOException {
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(String::trim)
                    .map(line -> {
                        try {
                            return parseLine(line);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid data, skipped: " + line + " (" + e.getMessage() + ")");
                            return Optional.<Student>empty();
                        } catch (Exception e) {
                            System.out.println("Error parsing line, skipped: " + line + " (" + e.getMessage() + ")");
                            return Optional.<Student>empty();
                        }
                    })
                    .flatMap(Optional::stream)
                    .limit(size)
                    .collect(Collectors.toList());
        }
    }

    private Optional<Student> parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Invalid format, skipped: " + line);
            return Optional.empty();
        }

        String groupNumber = parts[0].trim();
        String averageStr = parts[1].trim();
        String gradeBookStr = parts[2].trim();

        if (groupNumber.isEmpty()) {
            throw new IllegalArgumentException("groupNumber is empty");
        }

        double averageScore;
        try {
            averageScore = Double.parseDouble(averageStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("averageScore is not a valid number");
        }
        if (averageScore < minScore || averageScore > maxScore) {
            throw new IllegalArgumentException("averageScore out of range [" + minScore + "," + maxScore + "]");
        }

        int gradeBookNumber;
        try {
            gradeBookNumber = Integer.parseInt(gradeBookStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("gradeBookNumber is not a valid integer");
        }
        if (gradeBookNumber <= 0) {
            throw new IllegalArgumentException("gradeBookNumber must be positive");
        }

        Student student= Student.builder()
                .groupNumber(groupNumber)
                .averageScore(averageScore)
                .gradeBookNumber(gradeBookNumber)
                .build();
        return Optional.of(student);
    }
}



