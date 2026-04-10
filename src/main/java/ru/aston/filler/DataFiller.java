package ru.aston.filler;

import ru.aston.entity.Student;
import java.util.List;

public interface DataFiller {
    List<Student> fill(int size);
}