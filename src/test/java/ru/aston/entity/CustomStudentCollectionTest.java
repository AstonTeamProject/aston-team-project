package ru.aston.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomStudentCollectionTest {

    private CustomStudentCollection collection;

    @BeforeEach
    void setUp() {
        collection = new CustomStudentCollection();
    }

    @Test
    @DisplayName("Проверка добавления элементов и дубликатов")
    void testAddAndDuplicates() {
        Student s1 = Student.builder().groupNumber("1").gradeBookNumber(101).averageScore(5).build();
        Student s2 = Student.builder().groupNumber("1").gradeBookNumber(102).averageScore(6).build();
        Student sDuplicate = Student.builder().groupNumber("2").gradeBookNumber(101).averageScore(7).build();
        assertTrue(collection.add(s1));
        assertTrue(collection.add(s2));
        assertFalse(collection.add(null));
        assertFalse(collection.add(sDuplicate));
        assertEquals(2, collection.size());
    }

    @Test
    @DisplayName("Проверка метода get и исключений")
    void testGetAndBounds() {
        Student s1 = Student.builder().groupNumber("1").gradeBookNumber(101).averageScore(5).build();
        collection.add(s1);
        assertEquals(s1, collection.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> collection.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> collection.get(1));
    }

    @Test
    @DisplayName("Проверка метода set")
    void testSet() {
        Student s1 = Student.builder().groupNumber("1").gradeBookNumber(101).averageScore(5).build();
        Student s2 = Student.builder().groupNumber("2").gradeBookNumber(102).averageScore(8).build();
        collection.add(s1);
        Student old = collection.set(0, s2);
        assertEquals(s1, old);
        assertEquals(s2, collection.getFirst());
        assertThrows(IndexOutOfBoundsException.class, () -> collection.set(5, s2));
        assertThrows(IndexOutOfBoundsException.class, () -> collection.set(-1, s2));
    }

    @Test
    @DisplayName("Покрытие ensureCapacity (расширение массива)")
    void testEnsureCapacity() {
        for (int i = 1; i <= 11; i++) {
            collection.add(Student.builder().groupNumber("G").gradeBookNumber(i).averageScore(5).build());
        }
        assertEquals(11, collection.size());
        assertEquals(11, collection.get(10).getGradeBookNumber());
    }

    @Test
    @DisplayName("Покрытие toString (пустая и полная)")
    void testToString() {
        assertEquals("Collection is empty", collection.toString());
        collection.add(Student.builder().groupNumber("12").gradeBookNumber(123).averageScore(5.3).build());
        String toString = collection.toString();
        assertTrue(toString.contains("CustomStudentCollection"));
        assertTrue(toString.contains("123"));
    }
}
