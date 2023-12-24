package controllers;

import util.AdminChoice;
import util.UserInteractionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminController {
    public static void adminPart(Connection connection) throws SQLException {
        System.out.println("Что вы хотите сделать?");
        System.out.println("______________________________________________");
        AdminChoice adminChoice;
        UUID ID = null;
        ChildController childController = new ChildController();
        do {
            adminChoice = UserInteractionManager.makeAdminChoice();

            if (Objects.equals(adminChoice,AdminChoice.EXIT)){
                break;
            }
            switch (adminChoice){
                case ADDKINDERGARTEN:
                    System.out.println("Для доступа к данным данного детсада будет сгенерирован его ID");
                    System.out.println("Запомните его!");
                    ID = UUID.randomUUID();
                    System.out.println(ID);
                    KindergartenController kindergartenController = new KindergartenController();
                    kindergartenController.makeNewKindergartenAndAddToDB(connection,ID.toString());
                    break;
                case ADDCHILD:
                    System.out.println("Введите ID детского сада куда вы хотите добавить ребёнка");
                    String kindergartenID = UserInteractionManager.consoleInput.nextLine();
                    System.out.println("Для доступа к данным ребёнка будет сгенерирован его ID");
                    System.out.println("Запомните его!");
                    ID = UUID.randomUUID();
                    System.out.println(ID);
                    childController.createChildEntityAndAddToDB(connection,ID.toString(),kindergartenID);
                case DELET:
                    System.out.println("Введите ID ребёнка для удаления");
                    String childID = UserInteractionManager.consoleInput.nextLine();
                    childController.deleteChildByID(childID,connection);
                case GETALL:

                case UPDATE:
            }


            System.out.println("_______________________________________________________");
        }while (true);
    }
}
