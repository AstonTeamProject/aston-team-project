package ru.aston.entity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.*;
import java.util.stream.Stream;

public class StudentFileReaders {

    private static final Pattern LINE_PATTERN = Pattern.compile("^\\s*([\\p{L}0-9\\-]+)\\s*,\\s*([0-9]+(?:\\.[0-9]+)?)\\s*,\\s*([\\p{L}0-9\\-]+)\\s*$");
    private final InputStream in;
    private final double minGpa;
    private final double maxGpa;

    public StudentFileReaders(InputStream in, double minGpa, double maxGpa) {
        this.in = in;
        this.minGpa = minGpa;
        this.maxGpa = maxGpa;
    }

    public StudentRecord[] readAll() throws IOException {
        StudentShelf shelf = new StudentShelf();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
             Stream<String> lines = reader.lines()) {

            lines
                    .filter(line -> !line.isBlank())
                    .map(String::trim)
                    .map(this::parseLine)              // возвращает Optional<StudentRecord>
                    .flatMap(opt -> opt.map(Stream::of).orElseGet(Stream::empty))
                    .forEach(shelf::add);
        }

        return shelf.toArray();
    }

    private java.util.Optional<StudentRecord> parseLine(String line) {
        var m = LINE_PATTERN.matcher(line);
        if (!m.matches()) {
            System.err.println("Invalid format, skipped: " + line);
            return java.util.Optional.empty();
        }
        try {
            String group = m.group(1);
            double gpa = Double.parseDouble(m.group(2));
            int rb = Integer.parseInt(m.group(3));

            if (!group.matches("^[А-Яa-яЁё0-9\\-]{3,10}$")) {
                System.err.println("Invalid group number, skipped: " + line);
                return java.util.Optional.empty();
            }
            if (gpa < minGpa || gpa > maxGpa) {
                System.err.println("Invalid gpa range, skipped: " + line);
                return java.util.Optional.empty();
            }
            if (rb > 100000 || rb < 0) {
                System.err.println("Invalid record book format, skipped: " + line);
                return java.util.Optional.empty();
            }

            StudentRecord s = new StudentRecord.Builder()
                    .groupNumber(group)
                    .gpa(gpa)
                    .recordBook(rb)
                    .build();
            return java.util.Optional.of(s);
        } catch (Exception e) {
            System.err.println("Error parsing line, skipped: " + line + " (" + e.getMessage() + ")");
            return java.util.Optional.empty();
        }
    }
}
