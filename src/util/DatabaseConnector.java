package util;

import controllers.KindergartenController;
import controllers.ProgramController;
import entity.Child;
import entity.Kindergarten;
import repositories.ChildRepository;

import java.sql.*;
import java.util.List;

public class DatabaseConnector {
    private static String user = "root";
    private static String password = "1234";
    private static String URL = "jdbc:mysql://localhost:3306/education_sql";

    private static Connection tryToConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, user, password);
    }

    public static void executeDatabaseTasks() {
        try (Connection connection = tryToConnect()) {
            ProgramController.startProgram(connection);
        } catch (ClassNotFoundException | SQLException e) {
            // Обработка исключений (логирование, вывод сообщения и т.д.)
            e.printStackTrace();
        }
    }
}
