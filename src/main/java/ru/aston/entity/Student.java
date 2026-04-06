package ru.aston.entity;

import java.util.Locale;

public class Student {
    private final String groupNumber;
    private final double averageScore;
    private final int gradeBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.gradeBookNumber = builder.gradeBookNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getGradeBookNumber() {
        return gradeBookNumber;
    }

    public static class Builder {
        private String groupNumber;
        private double averageScore;
        private int gradeBookNumber;

        public Builder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder gradeBookNumber(int gradeBookNumber) {
            this.gradeBookNumber = gradeBookNumber;
            return this;
        }

        public Student build() {
            validate();
            return new Student(this);
        }

        private void validate() {
            if (groupNumber == null || groupNumber.isBlank()) {
                throw new IllegalArgumentException("Group number cant be empty");
            }

            if (gradeBookNumber <= 0) {
                throw new IllegalArgumentException("Grade book number can't be empty.");
            }
            if (averageScore <= 0 || averageScore > 10) {
                throw new IllegalArgumentException("Average score should be between 0 and 10");
            }
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT,"Student [group = '%s', score = %.2f, book = %d]",
                groupNumber, averageScore, gradeBookNumber);
    }
}
