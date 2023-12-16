package controllers;

import entity.Child;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProgramController {
    private Scanner consoleInput = new Scanner(System.in);
    public void startProgram(){
        System.out.println("Здравствуйте, вас приветствует демоверсия моего программного комплекса");

    }


    public Child createChildEntity(){
        String name = getChildName();
        int age = getChildAge();
        LocalDate dateOfBirth = getChildDateOfBirth();
        return new Child(name,age,dateOfBirth);
    }
    private String getChildName(){
        System.out.println("Введите имя ребёнка");
        return consoleInput.next();
    }
    private int getChildAge(){
       int age = isInteger();
       return age;
    }
    private int isInteger() {
        while (true) {
            try {
                String inputData = consoleInput.nextLine();
                int age = Integer.parseInt(inputData);
                if (age > 0) {
                    return age;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода , повторите");
            }
        }
    }

    private LocalDate getChildDateOfBirth(){

        System.out.println("Введите дату рождения ребенка (в формате YYYY-MM-DD):");

        // Читаем ввод пользователя
        String userInput = consoleInput.nextLine();

        // Используем DateTimeFormatter для преобразования строки в LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(userInput, formatter);
    }

}
