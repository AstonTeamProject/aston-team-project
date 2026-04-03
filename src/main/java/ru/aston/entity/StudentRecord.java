package ru.aston.entity;

public final class StudentRecord {
    private final String groupNumber;//номер группы
    private final double gpa;//средний бал
    private final int recordBook;//Номер зачетной книжки

    private StudentRecord(Builder b) {
        this.groupNumber = b.groupNumber;
        this.gpa = b.gpa;
        this.recordBook = b.recordBook;
    }

    public String getGroupNumber() { return groupNumber; }
    public double getGpa() { return gpa; }
    public int getRecordBook() { return recordBook; }

    public static class Builder {
        private String groupNumber;
        private Double gpa;
        private int recordBook;

        public Builder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber; return this;
        }
        public Builder gpa(double gpa) {
            this.gpa = gpa; return this;
        }
        public Builder recordBook(int recordBook) {
            this.recordBook = recordBook; return this;
        }
        public StudentRecord build() {
            if (groupNumber == null) throw new IllegalStateException("groupNumber required");
            if (gpa == null) throw new IllegalStateException("gpa required");
            if (recordBook == 0) throw new IllegalStateException("recordBook required");
            return new StudentRecord(this);
        }
    }

    @Override
    public String toString() {
        return "StudentRecord{" + "Группа =" + groupNumber + ", Средный бал =" + gpa + ", Номер зачётной книжки ='" + recordBook + "'}";
    }
}
