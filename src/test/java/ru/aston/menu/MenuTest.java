package ru.aston.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    static class TestMenu extends Menu {
        @Override
        public void show() {
        }
    }

    private void setInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void readInt_ShouldReturnNumber_WhenInputIsCorrect() {
        setInput("10\n");
        TestMenu menu = new TestMenu();

        int result = menu.readInt("Prompt");

        assertEquals(10, result);
    }

    @Test
    void readInt_ShouldRetry_WhenInputIsNotNumber() {
        setInput("abc\n5\n");
        TestMenu menu = new TestMenu();

        int result = menu.readInt("Prompt");

        assertEquals(5, result);
    }

    @Test
    void readString_ShouldReturnText() {
        setInput("Hello World\n");
        TestMenu menu = new TestMenu();

        String result = menu.readString("Prompt");

        assertEquals("Hello World", result);
    }
}
