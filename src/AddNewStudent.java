import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class AddNewStudent {
    private static Scanner consoleInput = new Scanner(System.in);
    private static Student createNewStudent(){
        System.out.println("Как вас зовут?");
        String name = consoleInput.nextLine();

        System.out.println("Ваша фамилия");
        String surname = consoleInput.nextLine();

        System.out.println("Какой у вас средний балл");
        double avg_grade = numberValidator();

        System.out.println("В каком институте вы учитесь");
        String institute = consoleInput.nextLine();

        System.out.println("На каком факультете вы учитесь?");
        String faculty = consoleInput.nextLine();

        return new Student(name,surname,avg_grade,faculty,institute);
    }



    private static double numberValidator(){
        String input_value = consoleInput.nextLine();
        while (true){
            try {
                double i = Double.parseDouble(input_value);
                return i;
            } catch (Exception e) {
                System.out.println("Введите число");;
            }
        }
    }


    public static void addStudentToDatabase(Connection connection) throws SQLException {
        Student student = createNewStudent();
        Statement statement = connection.createStatement();
        int [] ides = getFacultyAndInstituteId(statement,student);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (name , surname, avg_grade , fk_faculti_id, fk_institute_id)" +
                "VALUES(?,?,?,?,?)");
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getSurname());
        preparedStatement.setDouble(3,student.getAvg_grade());
        preparedStatement.setInt(4,ides[0]);
        preparedStatement.setInt(5,ides[1]);

        preparedStatement.executeUpdate();

    }

    public static int[] getFacultyAndInstituteId(Statement statement , Student student) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id FROM faculties WHERE name = '"+ student.getFaculty()+"'");
        int [] ides = new int[2];
        while (resultSet.next()){
            ides[0] = resultSet.getInt(1);
            System.out.println("id Факультета " + ides[0]);
        }
        ResultSet resultSet2 = statement.executeQuery("SELECT id FROM institutes WHERE name = '"+ student.getInstitute()+"'");
        while (resultSet2.next()){
            ides[1] = resultSet2.getInt(1);
            System.out.println("id Института " + ides[1]);
        }

        return ides;
    }







}
