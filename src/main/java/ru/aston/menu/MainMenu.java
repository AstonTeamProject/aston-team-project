package ru.aston.menu;

import ru.aston.entity.CustomStudentCollection;
import ru.aston.entity.Student;
import ru.aston.filler.FillerContext;
import ru.aston.filler.ManualFiller;
import ru.aston.io.StudentFileWriter;
import ru.aston.sorting.Sorter;
import ru.aston.threading.ThreadedCounter;

public class MainMenu extends Menu {
    private static final String MAIN_MENU_MSG = """ 
            --- MAIN MENU ---
            1. Filling
            2. Sorting
            3. Count occurrences
            4. Display collection
            5. Exit
            """;
    private static final String FILE_SAVE_MSG = "Save the result to a file? y / n (default)";
    private static final String FILE_PATH_SAVE_MSG = "Enter file path to save. Use an empty string for the default path: ";

    private CustomStudentCollection students = new CustomStudentCollection();

    @Override
    public void show() {
        System.out.println("Sorting App start");
        while (true) {
            int action = readInt(MAIN_MENU_MSG);
            switch (action) {
                case 1 -> fillMenu();
                case 2 -> sortMenu();
                case 3 -> countOccurrences();
                case 4 -> display();
                case 5 -> {
                    System.out.println("Sorting App finished");
                    return;
                }
                default -> System.out.println("Unknown action! Try again");
            }
        }
    }

    private void fillMenu() {
        FillMenu fillMenu = new FillMenu();
        fillMenu.show();
        int size;
        do {
            size = readInt("Enter array size (must be positive): ");
        } while (size <= 0);

        FillerContext filler = fillMenu.getFiller();
        students = (CustomStudentCollection) filler.fill(size);
    }

    private void sortMenu() {
        if (isEmptyCollection())
            return;

        SortMenu sortMenu = new SortMenu();
        sortMenu.show();
        Sorter sorter = sortMenu.getSorter();
        sorter.sort(students, sortMenu.getComparator());

        saveFileMenu();
    }

    private void countOccurrences() {
        if (isEmptyCollection())
            return;

        Student student = new ManualFiller(scanner).readStudent();
        int count = new ThreadedCounter().countOccurrences(students, student);
        System.out.printf("Count of occurrences %s = %d%n", student, count);
    }

    private void display() {
        if (isEmptyCollection())
            return;
        System.out.println(students);
    }

    private void saveFileMenu() {
        boolean isSaving = readString(FILE_SAVE_MSG).equals("y");
        if (isSaving) {
            String filePath = readString(FILE_PATH_SAVE_MSG);
            if (filePath.isBlank()) {
                new StudentFileWriter().appendStudents(students);
            } else {
                new StudentFileWriter(filePath.trim()).appendStudents(students);
            }
        }
    }

    private boolean isEmptyCollection() {
        if (students == null || students.isEmpty()) {
            System.out.println("Collection is empty. First fill the collection");
            return true;
        }
        return false;
    }
}