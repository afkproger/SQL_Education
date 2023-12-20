import util.DatabaseConnector;

import java.sql.*;
public class Main {

    //TODO:Добавить юзера и администратора
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector.executeDatabaseTasks();
    }
}