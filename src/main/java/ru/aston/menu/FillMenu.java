package ru.aston.menu;

import ru.aston.filler.FillerContext;
import ru.aston.filler.ManualFiller;
import ru.aston.filler.RandomFiller;
import ru.aston.filler.StudentFileReader;

public class FillMenu extends Menu {
    private static final String FILL_MENU_MSG = """
            --- FILL MENU ---
            1. File
            2. Random
            3. Manual
            """;
    private static final String FILE_PATH_READ_MSG = "Enter file path to read. Use an empty string for the default path: ";

    private final FillerContext filler = new FillerContext(null);

    @Override
    public void show() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;

            int choice = readInt(FILL_MENU_MSG);
            switch (choice) {
                case 1 -> filler.setDataFiller(getFileReader());
                case 2 -> filler.setDataFiller(new RandomFiller());
                case 3 -> filler.setDataFiller(new ManualFiller(scanner));
                default -> {
                    System.out.println("Unknown action! Try again");
                    isRunning = true;
                }
            }
        }
    }

    private StudentFileReader getFileReader() {
        String filePath = readString(FILE_PATH_READ_MSG);
        if (filePath.isBlank()) {
            return new StudentFileReader();
        }
        return new StudentFileReader(filePath.trim());
    }

    public FillerContext getFiller() {
        return filler;
    }
}