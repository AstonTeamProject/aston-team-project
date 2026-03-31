package ru.aston.entity;

public class Student {
    private final int groupNumber;
    private final double averageScore;
    private final String gradeBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.gradeBookNumber = builder.gradeBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getGradeBookNumber() {
        return gradeBookNumber;
    }

    public static class Builder {
        private int groupNumber;
        private double averageScore;
        private String gradeBookNumber;

        public Builder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder gradeBookNumber(String bookNumber) {
            this.gradeBookNumber = bookNumber;
            return this;
        }

        public Student build() {
            if (this.gradeBookNumber == null || this.gradeBookNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Grade book number cant be empty.");
            }
            if (this.averageScore < 0 || this.averageScore > 10) {
                throw new IllegalArgumentException("Average score should be between 0 and 10");
            }
            if (this.groupNumber <= 0) {
                throw new IllegalArgumentException("Group number should be positive.");
            }
            return new Student(this);
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", bookNumber='" + gradeBookNumber + '\'' +
                '}';
    }
}
