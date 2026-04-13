package ru.aston.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ParitySortTest {

    private ParitySort paritySort;
    private Comparator<Student> comparator;

    @BeforeEach
    void setUp() {
        paritySort = new ParitySort();
        comparator = Comparator.comparingInt(Student::getGradeBookNumber);
    }

    private Student createStudent(int gradeBookNumber){
        return Student.builder()
                .groupNumber("1123")
                .averageScore(5.1)
                .gradeBookNumber(gradeBookNumber)
                .build();
    }

    @Test
    void sort_ShouldSortOnlyEvenGradeNumbers() {
        List<Student> list = new ArrayList<>(Arrays.asList(
                createStudent(10),
                createStudent(5),
                createStudent(2),
                createStudent(3),
                createStudent(4)
        ));

        paritySort.sort(list, comparator);

        assertEquals(2, list.get(0).getGradeBookNumber());
        assertEquals(5, list.get(1).getGradeBookNumber());
        assertEquals(4, list.get(2).getGradeBookNumber());
        assertEquals(3, list.get(3).getGradeBookNumber());
        assertEquals(10, list.get(4).getGradeBookNumber());
    }

    @Test
    void sort_ShouldDoNothing_WhenAllNumbersAreOdd() {
        List<Student> list = new ArrayList<>(Arrays.asList(
                createStudent(1),
                createStudent(7),
                createStudent(3)
        ));

        List<Student> original = new ArrayList<>(list);
        paritySort.sort(list, comparator);

        assertEquals(original, list);
    }

    @Test
    void sort_ShouldWork_WhenAllNumbersAreEven() {
        List<Student> list = new ArrayList<>(Arrays.asList(
                createStudent(8),
                createStudent(2),
                createStudent(6)
        ));

        paritySort.sort(list, comparator);

        assertEquals(2, list.get(0).getGradeBookNumber());
        assertEquals(6, list.get(1).getGradeBookNumber());
        assertEquals(8, list.get(2).getGradeBookNumber());
    }

    @Test
    void sort_ShouldHandleEmptyAndNullList() {
        List<Student> emptyList = new ArrayList<>();
        assertDoesNotThrow(() -> paritySort.sort(emptyList, comparator));
        assertDoesNotThrow(() -> paritySort.sort(null, comparator));
    }

    @Test
    void builder_ShouldThrowException_OnInvalidData() {
        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("").gradeBookNumber(10).build());

        assertThrows(IllegalArgumentException.class, () ->
                Student.builder().groupNumber("A1").gradeBookNumber(-1).build());
    }

    @Test
    void sort_ShouldKeepOddElementsInPlaceWhileSortingEvens() {
        List<Student> list = new ArrayList<>(Arrays.asList(
                createStudent(1),
                createStudent(4),
                createStudent(5),
                createStudent(3),
                createStudent(2),
                createStudent(7),
                createStudent(9)
        ));

        paritySort.sort(list, comparator);

        assertEquals(2, list.get(1).getGradeBookNumber());
        assertEquals(4, list.get(4).getGradeBookNumber());
    }
}
