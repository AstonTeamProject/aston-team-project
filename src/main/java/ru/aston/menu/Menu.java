package ru.aston.menu;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public abstract void show();

    protected int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a number! Try again: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    protected String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}