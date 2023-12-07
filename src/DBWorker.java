import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {
    private static String user = "root";
    private static String password = "1234";
    private static String URL = "jdbc:mysql://localhost:3306/education_sql";

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,user,password);
    }


    public static void workWithConnection() throws SQLException, ClassNotFoundException {
        try(Connection connection = createConnection()) {
            System.out.println("Подключение установлено");
            //PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO faculties(name,count_of_students) VALUES (?,?)");
            AddNewStudent.addStudentToDatabase(connection);
        }
    }

}
