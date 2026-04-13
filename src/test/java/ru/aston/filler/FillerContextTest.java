package ru.aston.filler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FillerContextTest {

    private FillerContext fillerContext;
    private DataFiller mockFiller;

    @BeforeEach
    void setUp() {
        mockFiller = mock(DataFiller.class);
        fillerContext = new FillerContext(mockFiller);
    }

    @Test
    void fill_ShouldCallFillerMethod() {
        int size = 5;
        List<Student> expectedStudents = List.of(
                Student.builder().groupNumber("1").gradeBookNumber(1).averageScore(5).build()
        );
        when(mockFiller.fill(size)).thenReturn(expectedStudents);

        List<Student> result = fillerContext.fill(size);

        assertEquals(expectedStudents, result);
        verify(mockFiller, times(1)).fill(size);
    }

    @Test
    void fill_ShouldThrowException_WhenFillerIsNull() {
        fillerContext.setDataFiller(null);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> fillerContext.fill(10));
        assertEquals("DataFiller is not set.", exception.getMessage());
    }

    @Test
    void setDataFiller_ShouldChangeStrategy() {

        DataFiller newMockFiller = mock(DataFiller.class);

        fillerContext.setDataFiller(newMockFiller);

        assertEquals(newMockFiller, fillerContext.getDataFiller());
    }
}
