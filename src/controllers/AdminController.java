package controllers;

import entity.Kindergarten;
import repositories.KindergartenRepository;
import util.Choice;
import util.UserInteractionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AdminController {
    public static void adminPart(Connection connection) throws SQLException {
        System.out.println("Что вы хотите сделать?");
        System.out.println("______________________________________________");
        Choice adminChoice = UserInteractionManager.makeAdminChoice();
        switch (adminChoice){
            case ADD :
                System.out.println("Для доступа к данным данного детсада будет сгенерирован его ID");
                System.out.println("Запомните его!");
                UUID ID = UUID.randomUUID();
                KindergartenController kindergartenController = new KindergartenController();
                kindergartenController.makeNewKindergartenAndAddToDB(connection,ID.toString());
                break;
            case GETALL:

            case DELET:
        }
    }
}
