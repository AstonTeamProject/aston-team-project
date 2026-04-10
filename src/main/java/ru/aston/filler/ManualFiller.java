package ru.aston.filler;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualFiller implements DataFiller {

    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 10.0;
    private static final String SCORE_RANGE_MSG = "from " + MIN_SCORE + " to " + MAX_SCORE;

    private final Scanner scanner;

    public ManualFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Student> fill(int size) {
        System.out.println("\n--- Manual filling of student list ---");

        List<Student> students = IntStream.range(0, size)
                .mapToObj(i -> {
                    System.out.printf("\nStudent №%d:\n", i + 1);
                    return readStudent();
                })
                .collect(Collectors.toCollection(CustomStudentCollection::new));

        System.out.println("Manual filling completed.");
        return students;
    }

    public Student readStudent() {
        return Student.builder()
                .groupNumber(readGroupNumber())
                .averageScore(readAverageScore())
                .gradeBookNumber(readGradeBookNumber())
                .build();
    }

    private String readGroupNumber() {
        while (true) {
            System.out.print("Group number (non-empty string): ");
            String group = scanner.nextLine().trim();
            try {
                if (group.isBlank()) {
                    throw new IllegalArgumentException("Group number cannot be empty");
                }
                return group;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private double readAverageScore() {
        while (true) {
            System.out.print("Average score (number " + SCORE_RANGE_MSG + "): ");
            try {
                double score = Double.parseDouble(scanner.nextLine().trim());
                if (score < MIN_SCORE || score > MAX_SCORE) {
                    throw new IllegalArgumentException("Average score must be " + SCORE_RANGE_MSG);
                }
                return score;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number (e.g., 8.5).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private int readGradeBookNumber() {
        while (true) {
            System.out.print("Grade book number (integer > 0): ");
            try {
                int bookNumber = Integer.parseInt(scanner.nextLine().trim());
                if (bookNumber <= 0) {
                    throw new IllegalArgumentException("Grade book number must be positive");
                }
                return bookNumber;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
