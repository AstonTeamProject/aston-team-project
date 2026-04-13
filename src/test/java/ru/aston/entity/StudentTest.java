package ru.aston.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("Полное покрытие Equals и HashCode")
    void testEqualsAndHashCodeFullCoverage() {
        Student s1 = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();
        Student s2 = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();
        assertTrue(s1.equals(s1));
        assertFalse(s1.equals(null));
        assertFalse(s1.equals("String Object"));
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        Student diffScore = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.4)
                .build();
        assertFalse(s1.equals(diffScore));
        Student diffBook = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(999)
                .averageScore(5.3)
                .build();
        assertFalse(s1.equals(diffBook));
        Student diffGroup = Student.builder()
                .groupNumber("99")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();
        assertFalse(s1.equals(diffGroup));
    }

    @Test
    @DisplayName("Тесты Builder и валидации")
    void testBuilderValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("").gradeBookNumber(1).averageScore(5).build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber(null).gradeBookNumber(1).averageScore(5).build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("12").gradeBookNumber(0).averageScore(5).build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("12").gradeBookNumber(12).averageScore(11).build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("12").gradeBookNumber(12).averageScore(-1).build());
    }

    @Test
    @DisplayName("Тест геттеров и toString")
    void testGettersAndToString() {
        Student student = Student.builder()
                .groupNumber("12")
                .gradeBookNumber(12344)
                .averageScore(5.3)
                .build();

        assertEquals("12", student.getGroupNumber());
        assertEquals(12344, student.getGradeBookNumber());
        assertEquals(5.3, student.getAverageScore());
        assertEquals("Student [group = '12', score = 5.30, book = 12344]", student.toString());
    }
}
