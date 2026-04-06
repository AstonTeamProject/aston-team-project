package EntityTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;

public class StudentTest {

    @Test
    void testEmptyGroupNumber() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Student.builder().groupNumber("").build()
        );
        Assertions.assertEquals("Group number cant be empty", exception.getMessage());
    }

    @Test
    void testNullGroupNumber() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Student.builder().groupNumber(null).build()
        );
        Assertions.assertEquals("Group number cant be empty", exception.getMessage());
    }

    @Test
    void testInvalidGradeBookNumber() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Student.builder().groupNumber("12").gradeBookNumber(0).build()
        );
        Assertions.assertEquals("Grade book number can't be empty.", exception.getMessage());
    }

    @Test
    void testAverageScoreMoreThan10() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Student.builder().groupNumber("12").gradeBookNumber(12).averageScore(11).build()
        );
        Assertions.assertEquals("Average score should be between 0 and 10", exception.getMessage());
    }

    @Test
    void testAverageScoreLessThan0() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Student.builder().groupNumber("12").gradeBookNumber(12).averageScore(-1).build()
        );
        Assertions.assertEquals("Average score should be between 0 and 10", exception.getMessage());
    }

    @Test
    void testSuccessfulBuild(){
        Student student = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();
        Assertions.assertEquals("12", student.getGroupNumber());
        Assertions.assertEquals(12344, student.getGradeBookNumber());
        Assertions.assertEquals(5.3, student.getAverageScore());
    }

    @Test
    void testCorrectToString(){
        Student student = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();
        Assertions.assertEquals("12", student.getGroupNumber());
        Assertions.assertEquals(12344, student.getGradeBookNumber());
        Assertions.assertEquals(5.3, student.getAverageScore());
        Assertions.assertEquals("Student [group = '12', score = 5.30, book = 12344]",student.toString());
    }
}
