package ru.aston.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.entity.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentFileWriterTest {

    @TempDir
    Path tempDir;

    private Path tempFile;
    private StudentFileWriter writer;

    @BeforeEach
    void setUp() {
        tempFile = tempDir.resolve("test_students.txt");
        writer = new StudentFileWriter(tempFile.toString());
    }

    @Test
    void appendStudents_ShouldCreateFileAndWriteData() throws IOException {

        Student student = Student.builder()
                .groupNumber("A-1")
                .averageScore(9.5)
                .gradeBookNumber(101)
                .build();
        List<Student> students = List.of(student);

        writer.appendStudents(students);

        assertTrue(Files.exists(tempFile));

        List<String> lines = Files.readAllLines(tempFile);

        assertTrue(lines.get(0).startsWith("--- "));
        assertEquals(student.toString(), lines.get(1));
    }

    @Test
    void appendStudents_ShouldAppendDataToExistingFile() throws IOException {

        Student s1 = Student.builder().groupNumber("G1").averageScore(5).gradeBookNumber(2).build();
        Student s2 = Student.builder().groupNumber("G2").averageScore(6).gradeBookNumber(4).build();

        writer.appendStudents(List.of(s1));
        writer.appendStudents(List.of(s2));

        List<String> lines = Files.readAllLines(tempFile);

        long student1Count = lines.stream().filter(l -> l.equals(s1.toString())).count();
        long student2Count = lines.stream().filter(l -> l.equals(s2.toString())).count();

        assertEquals(1, student1Count);
        assertEquals(1, student2Count);
        assertTrue(lines.size() >= 4);
    }

    @Test
    void appendStudents_ShouldHandleEmptyList() throws IOException {

        writer.appendStudents(List.of());

        assertTrue(Files.exists(tempFile));
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size());
    }

    @Test
    void defaultConstructor_ShouldInitializeWithDefaultPath() {
        StudentFileWriter defaultWriter = new StudentFileWriter();
        assertNotNull(defaultWriter);
    }

    @Test
    void appendStudents_ShouldHandleIOException() {
        StudentFileWriter errorWriter = new StudentFileWriter(tempDir.toString());

        Student student = Student.builder()
                .groupNumber("Test")
                .averageScore(5.0)
                .gradeBookNumber(123)
                .build();

        assertDoesNotThrow(() -> errorWriter.appendStudents(List.of(student)));
    }
}
