import controllers.DatabaseConnector;

import java.sql.*;
//Class.forName("com.mysql.jdbc.Driver");
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector.executeDatabaseTasks();
    }
}