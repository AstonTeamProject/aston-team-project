package ru.aston;

import entity.CustomStudentCollection;
import entity.Student;

public class Main {
    public static void main(String[] args) {
        //Создаем студента. Конструктор приватный, поэтому создаем строго через билдер. Номер зачетки должен быть уникальным, иначе не создаст.
        try {
            Student student = new Student.Builder()
                    .groupNumber(12)                //Номер группы (int), groupNumber > 0
                    .gradeBookNumber("AB123")       //Номер зачетки (String)
                    .averageScore(5.2)              // Средний балл (double), 0 < averageScore <= 10
                    .build();
            System.out.println("Created student: " + student);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Генерация рандомных студентов(Посредством стрима)
        CustomStudentCollection customStudentCollection = CustomStudentCollection.generate(5, () -> {
            int randomGroup = (int) (Math.random() * 10) + 1;
            double randomAverageScore = Math.random() * 10;
            String randomGradeBookNumber = "ID" + Math.random();
            return new Student.Builder()
                    .groupNumber(randomGroup)
                    .averageScore(randomAverageScore)
                    .gradeBookNumber(randomGradeBookNumber)
                    .build();
        });
        System.out.println(customStudentCollection);

        //--------------------Для того, кто пишет сортировку-----------------------------------

        //Проход по кастомной коллекции
        for(Student student: customStudentCollection){
            System.out.println("Average score: " + student.getAverageScore());
        }
        //через метод get(i) можно дастать конкретный элемент
        Student firstStudent = customStudentCollection.get(0); //взяли первого студента
        Student secondStudent = customStudentCollection.get(1); // взяли второго студента
        System.out.println("The first two students: " + firstStudent + "\n" + secondStudent);

        //Через метод set(int i, Student student) вставляем конкретного student по индексу i
        Student temp = firstStudent;
        customStudentCollection.set(0,secondStudent);
        customStudentCollection.set(1,temp);
        System.out.println("Swap the first two students: " + customStudentCollection.get(0) + "\n" + customStudentCollection.get(1));
    }
}