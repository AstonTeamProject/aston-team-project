package ru.aston.readFromFile;

import ru.aston.entity.Student;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentMainReader {
    private final String path;

    public StudentMainReader(String path) {
        this.path = path;
    }

    public void insertStudents(List<Student> studentList) throws IOException {
        try (InputStream in = StudentMainReader.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                System.err.println("Resource " + path + " not found in classpath");
                return;
            }

            StudentFileReaders reader = new StudentFileReaders(in, 0.0, 5.0);
            Student[] arr = reader.readAll();

            System.out.println("Read " + arr.length + " students");
            for (Student student : arr) {
                studentList.add(student);
            }
        }
    }

    //Для удобства оставлю,чтобы можно было проверить.
    /*public static void main(String[] args) throws IOException {
        StudentMainReader testReader = new StudentMainReader("students.txt");
        List<Student> students = new ArrayList<>();
        testReader.insertStudents(students);

        for (Student student : students) {
            System.out.println("Student: " + student);
        }
    }*/
}
