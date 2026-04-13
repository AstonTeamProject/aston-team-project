package ru.aston.menu;

import org.junit.jupiter.api.Test;
import ru.aston.sorting.ParitySort;
import ru.aston.sorting.QuickSort;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class SortMenuTest {

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void show_ShouldSetQuickSortAndGroupComparator() {
        provideInput("1\n1\n");
        SortMenu sortMenu = new SortMenu();

        sortMenu.show();

        assertTrue(sortMenu.getSorter().getSortStrategy() instanceof QuickSort);
        assertNotNull(sortMenu.getComparator());
    }

    @Test
    void show_ShouldSetParitySortAndGradeBookComparator() {
        provideInput("5\n2\n");
        SortMenu sortMenu = new SortMenu();

        sortMenu.show();

        assertTrue(sortMenu.getSorter().getSortStrategy() instanceof ParitySort);
        assertNotNull(sortMenu.getComparator());
    }

    @Test
    void show_ShouldHandleOtherFields() {
        provideInput("2\n2\n");
        SortMenu sortMenu = new SortMenu();

        sortMenu.show();

        assertTrue(sortMenu.getSorter().getSortStrategy() instanceof QuickSort);
    }

    @Test
    void show_ShouldRetryOnUnknownAction() {
        provideInput("9\n1\n1\n1\n");
        SortMenu sortMenu = new SortMenu();

        assertDoesNotThrow(sortMenu::show);
    }
}
