package filler;

import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;
import ru.aston.filler.ManualFiller;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class ManualFillerTest {

    @Test
    void testFillFullCoverage() {
        String input = String.join("\n",
                "",
                "Group-A",
                "abc",
                "15.0",
                "-1.0",
                "8.5",
                "not_int",
                "0",
                "101",
                "Group-B",
                "7.0",
                "102"
        );
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ManualFiller filler = new ManualFiller(scanner);
        List<Student> result = filler.fill(2);
        assertEquals(2, result.size());
        Student s1 = result.get(0);
        assertEquals("Group-A", s1.getGroupNumber());
        assertEquals(8.5, s1.getAverageScore());
        assertEquals(101, s1.getGradeBookNumber());
        Student s2 = result.get(1);
        assertEquals("Group-B", s2.getGroupNumber());
        assertEquals(102, s2.getGradeBookNumber());
    }

    @Test
    void testFillEmptySize() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        ManualFiller filler = new ManualFiller(scanner);
        List<Student> result = filler.fill(0);
        assertTrue(result.isEmpty());
    }
}