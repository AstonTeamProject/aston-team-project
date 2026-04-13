package ru.aston.threading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadedCounterTest {

    private ThreadedCounter counter;
    private Student target;

    @BeforeEach
    void setUp() {
        counter = new ThreadedCounter();
        target = Student.builder()
                .groupNumber("1")
                .gradeBookNumber(100)
                .averageScore(5.0)
                .build();
    }

    @Test
    void testEmptyAndNullList() {
        assertEquals(0, counter.countOccurrences(null, target));
        assertEquals(0, counter.countOccurrences(Collections.emptyList(), target));
    }

    @Test
    void testCountOccurrences() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                students.add(target);
            } else {
                students.add(Student.builder()
                        .groupNumber("2")
                        .gradeBookNumber(i + 1000)
                        .averageScore(1.0)
                        .build());
            }
        }

        int result = counter.countOccurrences(students, target);
        assertEquals(10, result);
    }

    @Test
    void testSmallList() {
        List<Student> students = List.of(target);
        int result = counter.countOccurrences(students, target);
        assertEquals(1, result);
    }

    @Test
    void testNoMatches() {
        List<Student> students = List.of(
                Student.builder().groupNumber("99").gradeBookNumber(999).averageScore(9.9).build()
        );
        int result = counter.countOccurrences(students, target);
        assertEquals(0, result);
    }

    @Test
    void testInterruptAndTimeout() throws InterruptedException {
        Student s1 = Student.builder().groupNumber("1").gradeBookNumber(1).averageScore(5).build();
        List<Student> sleepyList = new AbstractList<>() {
            @Override
            public Student get(int index) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return s1;
            }
            @Override
            public int size() { return 1; }
        };
        Thread t = new Thread(() -> counter.countOccurrences(sleepyList, s1));
        t.start();
        Thread.sleep(300);
        t.interrupt();
        t.join();
        counter.countOccurrences(sleepyList, s1);
    }
}
