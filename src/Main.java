import util.DatabaseConnector;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector.executeDatabaseTasks();
    }
}