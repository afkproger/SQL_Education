package controllers;

import entity.Child;
import repositories.ChildRepository;
import util.UserChoice;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ChildController {
    private final Scanner consoleInput = new Scanner(System.in);
    public   void deleteChildByID(String ID , Connection connection) throws SQLException {
        ChildRepository childRepository  = new ChildRepository(connection);
        childRepository.deleteChildByIndex(ID);
    }

    public   Child getChildById(String ID, Connection connection) throws SQLException {
        ChildRepository childRepository = new ChildRepository(connection);

        return childRepository.getChildById(ID);
    }

    public   void updateDataAboutChild(Connection connection , String ID) throws SQLException {
        Child child = createChild();
        ChildRepository childRepository = new ChildRepository(connection);
        childRepository.updateInfoAboutChild(child,ID);
    }
    public List<Child> getChildren(Connection connection) throws SQLException {
        ChildRepository childRepository = new ChildRepository(connection);
        return childRepository.getAllChild();
    }

    private  Child createChild(){
        String name = getChildName();
        String surname = getChildSurname();
        LocalDate dateOfBirth = getChildDateOfBirth();
        // мы достали текущий год из LocalDate.now() и парсил его методом .getValue() от класса Year
        int age = Year.from(LocalDate.now()).getValue() - dateOfBirth.getYear();
        return new Child(name,surname,age,dateOfBirth);
    }
    public void createChildEntityAndAddToDB(Connection connection, String ID) throws SQLException {
        Child child = createChild();
        ChildRepository childRepository = new ChildRepository(connection);
        childRepository.addChildToDB(child , ID);
    }
    private  String getChildName(){
        System.out.println("Введите имя ребёнка");
        return consoleInput.nextLine();
    }
    private  String getChildSurname(){
        System.out.println("Введите фамилию ребёнка");
        return consoleInput.nextLine();
    }
    private  int isInteger() {
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

    public   LocalDate getChildDateOfBirth(){

        System.out.println("Введите дату рождения ребенка (в формате YYYY-MM-DD):");

        // Читаем ввод пользователя
        String userInput = consoleInput.nextLine();

        // Используем DateTimeFormatter для преобразования строки в LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(userInput, formatter);
    }



}