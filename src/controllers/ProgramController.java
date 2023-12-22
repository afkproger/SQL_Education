package controllers;
import java.util.UUID;
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

public class ProgramController {
    private static final Scanner consoleInput = new Scanner(System.in);
    public static void startProgram(Connection connection) throws SQLException {
        System.out.println("Здравствуйте, вас приветствует демоверсия моего программного комплекса");
        System.out.println("____________________________МЕНЮ________________________________________");
        UserChoice choice = makeChoice();
        ChildController childController = new ChildController();

        switch (choice){
            case ADD:
                System.out.println("При добавлении нового ребёнка , генерируется его идентификатор");
                System.out.println("Запомните его!");
                String identifier = UUID.randomUUID().toString();
                System.out.println(identifier);
                childController.createChildEntityAndAddToDB(connection,identifier);
                break;
            case GETBYID:
                System.out.println("Введите ID вашего ребёнка");
                String childID = consoleInput.nextLine();
                System.out.println(childController.getChildById(childID , connection));
                break;
            case GETALL:
                List<Child> children = childController.getChildren(connection);
                children.forEach(System.out::println);
                break;

            case DELET:
                System.out.println("Введите ID вашего ребёнка");
                String childIDForDelete = consoleInput.nextLine();
                childController.deleteChildByID(childIDForDelete,connection);
                break;
            case UPDATE:
                System.out.println("Введите ID вашего ребёнка");
                String childIDForUpdate = consoleInput.nextLine();
                childController.updateDataAboutChild(connection,childIDForUpdate);
                System.out.println("_____________________________");
                System.out.println(childController.getChildById(childIDForUpdate , connection));
                break;
        }
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
