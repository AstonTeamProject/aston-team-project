package ru.aston.io;

import ru.aston.entity.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentFileWriter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static final String FILE_PATH = "src/main/resources/sorted_students.txt";
    private final Path filePath;

    public StudentFileWriter() {
        this.filePath = Paths.get(FILE_PATH);
    }

    public StudentFileWriter(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void appendStudents(List<Student> students) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        )) {
            writer.write("--- " + LocalDateTime.now().format(DATE_FORMATTER) + " ---");
            writer.newLine();
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Student list has been appended to file: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}