package ru.aston.filler;

import ru.aston.entity.Student;
import java.util.List;
import java.util.Scanner;

public interface DataFiller {
    List<Student> fill(Scanner scanner, int size);
}
