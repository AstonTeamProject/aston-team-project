package ru.aston.filler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.entity.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentFileReaderTest {
    @TempDir
    Path tempDir;

    private Path tempFile;
    private StudentFileReader reader;

    @BeforeEach
    void setUp() {
        tempFile = tempDir.resolve("students_input.txt");
        reader = new StudentFileReader(tempFile.toString());
    }

    @Test
    void fill_ShouldReadAndParseValidStudents() throws IOException {
        List<String> lines = List.of(
                "A1, 8.5, 101",
                "  ",
                "INVALID_DATA",
                "B2, 11.0, 102",
                "C3, NotNumber, 103",
                "D4, 9.0, 104"
        );
        Files.write(tempFile, lines);

        List<Student> result = reader.fill(2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A1", result.get(0).getGroupNumber());
        assertEquals("D4", result.get(1).getGroupNumber());
    }

    @Test
    void readAll_ShouldReturnNull_WhenFileDoesNotExist() {
        StudentFileReader nonExistentReader = new StudentFileReader("non_existent_file.txt");
        assertNull(nonExistentReader.readAll(10));
    }

    @Test
    void readAll_ShouldReturnNull_WhenPathIsDirectory() {
        StudentFileReader dirReader = new StudentFileReader(tempDir.toString());
        assertNull(dirReader.readAll(10));
    }

    @Test
    void parseLine_ShouldHandleInvalidArguments() throws IOException {
        List<String> lines = List.of(
                ", 5.0, 100",
                "A1, 5.0, -1"
        );
        Files.write(tempFile, lines);

        List<Student> result = reader.readAll(10);
        assertTrue(result.isEmpty());
    }

    @Test
    void defaultConstructor_ShouldInitialize() {
        StudentFileReader defaultReader = new StudentFileReader();
        assertNotNull(defaultReader);
    }

}
