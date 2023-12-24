package controllers;

import entity.Child;
import util.UserChoice;
import util.UserInteractionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserController {
    public static void userPart(Connection connection) throws SQLException, SQLException {
        UserChoice choice = UserInteractionManager.makeUserChoice();
        ChildController childController = new ChildController();
        switch (choice){
            case GETBYID:
                System.out.println("Введите ID вашего ребёнка");
                String childID = UserInteractionManager.consoleInput.nextLine();
                System.out.println(childController.getChildById(childID , connection));
                break;
            case GETALL:
                List<Child> children = childController.getChildren(connection);
                children.forEach(System.out::println);
                break;

        }
    }
}
