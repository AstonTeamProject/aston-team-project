package ru.aston.filler;

import ru.aston.entity.Student;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StudentFileReader {
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 10.0;
    private static final int EXPECTED_PARTS_COUNT = 3;
    private static final String DELIMITER = ",";

    private final Path filePath;

    public StudentFileReader(String path) {
        this.filePath = Path.of(path);
    }
    
    public List<Student> readAll(int size) throws IOException {
    if (Files.notExists(filePath)) {
        throw new FileNotFoundException("File does not exist: " + filePath);
    }
    if (Files.isDirectory(filePath)) {
        throw new IllegalArgumentException("Path is a directory, not a file: " + filePath);
    }
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .flatMap(Optional::stream)
                    .limit(size)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println();
        }
        return null;
    }
    
    private Optional<Student> parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != EXPECTED_PARTS_COUNT) {
            System.out.println("Invalid format, skipped: " + line);
            return Optional.empty();
        }
        Student student;
        try {
            String groupNumber = parts[0].trim();
            double averageScore = Double.parseDouble(parts[1].trim());
            int gradeBookNumber = Integer.parseInt(parts[2].trim());

            if (groupNumber.isEmpty()) {
                throw new IllegalArgumentException("groupNumber is empty");
            }
            if (averageScore < MIN_SCORE || averageScore > MAX_SCORE) {
                throw new IllegalArgumentException("averageScore out of range [" + MIN_SCORE + "," + MAX_SCORE + "]");
            }
            if (gradeBookNumber <= 0) {
                throw new IllegalArgumentException("gradeBookNumber must be positive");
            }
            student = Student.builder()
                    .groupNumber(groupNumber)
                    .averageScore(averageScore)
                    .gradeBookNumber(gradeBookNumber)
                    .build();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(student);
    }
}



