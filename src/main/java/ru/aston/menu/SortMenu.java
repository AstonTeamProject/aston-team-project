package ru.aston.menu;

import ru.aston.entity.Student;
import ru.aston.sorting.ParitySort;
import ru.aston.sorting.QuickSort;
import ru.aston.sorting.SortDirection;
import ru.aston.sorting.Sorter;
import ru.aston.sorting.comparator.StudentComparatorFactory;

import java.util.Comparator;
import java.util.Map;

public class SortMenu extends Menu {
    private static final String SORT_MENU_MSG = """ 
            --- SORT MENU ---
            1. By group number
            2. By average score
            3. By grade book number
            4. By all
            5. By parity
            """;
    private static final String DIRECTION_MENU_MSG = """
            Select sorting direction:
            1. Ascending
            2. Descending (default)
            """;
    private static final Map<Integer, StudentComparatorFactory.Field> CODE_TO_FIELD = Map.of(
            1, StudentComparatorFactory.Field.GROUP_NUMBER,
            2, StudentComparatorFactory.Field.AVERAGE_SCORE,
            3, StudentComparatorFactory.Field.GRADE_BOOK_NUMBER,
            4, StudentComparatorFactory.Field.ALL
    );

    private Comparator<Student> comparator;
    private final Sorter sorter = new Sorter(null);

    @Override
    public void show() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;

            int fieldChoice = readInt(SORT_MENU_MSG);
            SortDirection direction = readInt(DIRECTION_MENU_MSG) == 1 ? SortDirection.ASC : SortDirection.DESC;
            switch (fieldChoice) {
                case 1, 2, 3, 4 -> {
                    comparator = StudentComparatorFactory.create(
                            CODE_TO_FIELD.get(fieldChoice),
                            direction
                    );
                    sorter.setStrategy(new QuickSort());
                }
                case 5 -> {
                    comparator = StudentComparatorFactory.create(
                            StudentComparatorFactory.Field.GRADE_BOOK_NUMBER,
                            direction
                    );
                    sorter.setStrategy(new ParitySort());
                }
                default -> {
                    System.out.println("Unknown action! Try again");
                    isRunning = true;
                }
            }
        }
    }

    public Comparator<Student> getComparator() {
        return comparator;
    }

    public Sorter getSorter() {
        return sorter;
    }
}