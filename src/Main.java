import util.DatabaseConnector;

import java.sql.*;
public class Main {
    //TODO: Дописать логику администратору
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector.executeDatabaseTasks();
    }
}