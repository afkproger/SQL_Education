package controllers;

import entity.Child;
import repositories.ChildRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProgramController {
    private static final Scanner consoleInput = new Scanner(System.in);
    public static void startProgram(Connection connection) throws SQLException {
        System.out.println("Здравствуйте, вас приветствует демоверсия моего программного комплекса");
        System.out.println("____________________________МЕНЮ________________________________________");
        UserChoice choice = makeChoice();

        switch (choice){
            case ADD:
                createChildEntityAndAddToDB(connection);
                break;
            case GETBYID:
                System.out.println("Введите ID вашего ребёнка");
                int childID = isInteger();
                System.out.println(getChildById(childID , connection));
                break;
            case GETALL:
                List<Child> children = getChildren(connection);
                children.forEach(System.out::println);
                break;

            case DELET:
                System.out.println("Введите ID вашего ребёнка");
                int childIDForDelete = isInteger();
                deleteChildByID(childIDForDelete,connection);
                break;
            case UPDATE:
                System.out.println("Введите ID вашего ребёнка");
                int childIDForUpdate = isInteger();
                updateDataAboutChild(connection,childIDForUpdate);
                System.out.println("_____________________________");
                System.out.println(getChildById(childIDForUpdate , connection));
                break;


        }
    }

    private static void deleteChildByID(int id , Connection connection) throws SQLException {
        ChildRepository childRepository  = new ChildRepository(connection);
        childRepository.deleteChildByIndex(id);
    }

    private static Child getChildById(int id , Connection connection) throws SQLException {
        ChildRepository childRepository = new ChildRepository(connection);
        return childRepository.getChildById(id);
    }

    private static void updateDataAboutChild(Connection connection , int ID) throws SQLException {
        Child child = createChild();
        ChildRepository childRepository = new ChildRepository(connection);
        childRepository.updateInfoAboutChild(child,ID);
    }
    private static List<Child> getChildren(Connection connection) throws SQLException {
        ChildRepository childRepository = new ChildRepository(connection);
        return childRepository.getAllChild();
    }

    private static Child createChild(){
        String name = getChildName();
        //TODO:Имеет смысл просто вводить дату рождения, а программа сама высчитывает возраст ребёнка
        int age = getChildAge();
        LocalDate dateOfBirth = getChildDateOfBirth();
        return new Child(name,age,dateOfBirth);
    }
    private static void createChildEntityAndAddToDB(Connection connection) throws SQLException {
        Child child = createChild();
        ChildRepository childRepository = new ChildRepository(connection);
        childRepository.addChildToDB(child);
    }
    private static String getChildName(){
        System.out.println("Введите имя ребёнка");
        return consoleInput.nextLine();
    }
    private static int getChildAge(){
        System.out.println("Введите возраст ребёнка");
       int age = isInteger();
       return age;
    }
    private static int isInteger() {
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

    private  static LocalDate getChildDateOfBirth(){

        System.out.println("Введите дату рождения ребенка (в формате YYYY-MM-DD):");

        // Читаем ввод пользователя
        String userInput = consoleInput.nextLine();

        // Используем DateTimeFormatter для преобразования строки в LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(userInput, formatter);
    }

    private static UserChoice makeChoice(){
        System.out.println("1 - Добавить ребёнка в базу");
        System.out.println("2 - Получить информацию о ребёнке");
        System.out.println("3 - Получить информацию о всех детях");
        System.out.println("4 - Удалить ребёнка из базы");
        System.out.println("5 - Обновить информацию о ребёнке");

        int choice = isInteger();

        while (true){
            switch (choice){
                case 1:
                    return UserChoice.ADD;

                case 2:
                    return UserChoice.GETBYID;

                case 3:
                    return UserChoice.GETALL;

                case 4:
                    return UserChoice.DELET;

                case 5:
                    return UserChoice.UPDATE;

                default:
                    System.out.println("Нет такого пункта, повторите ввод");
            }
        }
    }

}
