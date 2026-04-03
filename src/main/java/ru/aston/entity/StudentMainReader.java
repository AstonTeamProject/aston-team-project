package ru.aston.entity;

import java.io.InputStream;

public class StudentMainReader {
    public static void main(String[] args) throws Exception {

        try (InputStream in = StudentMainReader.class.getClassLoader().getResourceAsStream("students.txt")) {
            if (in == null) {
                System.err.println("Resource students.txt not found in classpath");
                return;
            }


            StudentFileReaders reader = new StudentFileReaders(in, 0.0, 5.0);
            StudentRecord[] arr = reader.readAll();

            System.out.println("Read " + arr.length + " students");
            for (StudentRecord s : arr) System.out.println(s);
        }
    }
}
