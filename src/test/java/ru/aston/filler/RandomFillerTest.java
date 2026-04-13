package ru.aston.filler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomFillerTest {

    private RandomFiller randomFiller;

    @BeforeEach
    void setUp() {
        randomFiller = new RandomFiller();
    }

    @Test
    void fill_ShouldReturnCollectionWithCorrectSize() {
        int size = 10;
        List<Student> students = randomFiller.fill(size);

        assertNotNull(students);
        assertEquals(size, students.size());
    }

    @Test
    void fill_ShouldGenerateValidStudentData() {
        int size = 50;
        List<Student> students = randomFiller.fill(size);

        for (Student s : students) {
            assertTrue(s.getAverageScore() >= 0.0 && s.getAverageScore() <= 10.0);

            assertTrue(s.getGradeBookNumber() >= 0 && s.getGradeBookNumber() < 999999);

            assertNotNull(s.getGroupNumber());
            assertTrue(s.getGroupNumber().contains("-"));
        }
    }

    @Test
    void fill_ShouldReturnEmptyList_WhenSizeIsZero() {
        List<Student> students = randomFiller.fill(0);
        assertTrue(students.isEmpty());
    }

    @Test
    void fill_ShouldHandleLargeSize() {
        assertDoesNotThrow(() -> randomFiller.fill(1000));
    }
}
