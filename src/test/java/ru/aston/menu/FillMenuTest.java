package ru.aston.menu;

import org.junit.jupiter.api.Test;
import ru.aston.filler.ManualFiller;
import ru.aston.filler.RandomFiller;
import ru.aston.filler.StudentFileReader;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class FillMenuTest {

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void show_ShouldSetRandomFiller() {
        provideInput("2\n");
        FillMenu fillMenu = new FillMenu();

        fillMenu.show();

        assertNotNull(fillMenu.getFiller().getDataFiller());
        assertTrue(fillMenu.getFiller().getDataFiller() instanceof RandomFiller);
    }

    @Test
    void show_ShouldSetManualFiller() {
        provideInput("3\n");
        FillMenu fillMenu = new FillMenu();

        fillMenu.show();

        assertTrue(fillMenu.getFiller().getDataFiller() instanceof ManualFiller);
    }

    @Test
    void show_ShouldSetFileReaderWithDefaultPath() {
        provideInput("1\n\n");
        FillMenu fillMenu = new FillMenu();

        fillMenu.show();

        assertTrue(fillMenu.getFiller().getDataFiller() instanceof StudentFileReader);
    }

    @Test
    void show_ShouldSetFileReaderWithCustomPath() {
        provideInput("1\ncustom.txt\n");
        FillMenu fillMenu = new FillMenu();

        fillMenu.show();

        assertTrue(fillMenu.getFiller().getDataFiller() instanceof StudentFileReader);
    }

    @Test
    void show_ShouldRetryOnUnknownAction() {
        provideInput("9\n2\n");
        FillMenu fillMenu = new FillMenu();

        assertDoesNotThrow(fillMenu::show);
        assertTrue(fillMenu.getFiller().getDataFiller() instanceof RandomFiller);
    }
}
