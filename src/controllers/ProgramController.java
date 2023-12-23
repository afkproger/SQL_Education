package controllers;
import util.HowToLoginChoice;
import util.UserInteractionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class ProgramController {

    public static void startProgram(Connection connection) throws SQLException {
        System.out.println("Здравствуйте, вас приветствует демоверсия моего программного комплекса");
        System.out.println("____________________________МЕНЮ________________________________________");
        HowToLoginChoice choice = UserInteractionManager.makeLoginChoice();
        switch (choice){
            case ADMIN :
                AdminController.adminPart(connection);
                break;
            case USER:
                UserController.userPart(connection);
                break;
        }

    }


}
