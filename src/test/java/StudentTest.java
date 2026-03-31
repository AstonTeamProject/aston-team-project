import org.junit.jupiter.api.Test;
import entity.Student;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testValidStudentCreation() {
        Student student = new Student.Builder()
                .groupNumber(1)
                .averageScore(5.0)
                .gradeBookNumber("St123")
                .build();
        assertEquals("St123", student.getGradeBookNumber());
        assertEquals(1, student.getGroupNumber());
        assertEquals(5.0, student.getAverageScore());
    }

    @Test
    void testExceptionNotValidGroupNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student.Builder().groupNumber(-1)
                    .averageScore(5.0)
                    .gradeBookNumber("St123")
                    .build();
        });
        assertEquals("Group number should be positive.", exception.getMessage());
    }

    @Test
    void testExceptionNotValidAverageScore(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            new Student.Builder().groupNumber(1)
                    .averageScore(11.3)
                    .gradeBookNumber("St123")
                    .build();
        });
        assertEquals("Average score should be between 0 and 10", exception.getMessage());
    }

    @Test
    void testExceptionNotValidGradeBookNumber(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            new Student.Builder().groupNumber(1)
                    .averageScore(9)
                    .gradeBookNumber("")
                    .build();
        });
        assertEquals("Grade book number cant be empty.", exception.getMessage());
    }
}
