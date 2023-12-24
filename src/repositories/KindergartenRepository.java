package repositories;

import entity.Kindergarten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class KindergartenRepository {
    private final Connection connector ;

    public KindergartenRepository(Connection connector) {
        this.connector = connector;
    }

    public void addNewKindergarten(Kindergarten kindergarten , String ID) throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("INSERT INTO kindergarten (name, location , identifier) VALUES(?,?,?)");
        preparedStatement.setString(1, kindergarten.getName());
        preparedStatement.setString(2, kindergarten.getLocation());
        preparedStatement.setString(3,ID);
        preparedStatement.executeUpdate();
    }

    public void getAllInfo() throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("select children.name , children.surname , children.date_of_birth, " +
                        "kindergarten.name ,kindergarten.location from children JOIN kindergarten on " +
                        "children.fk_kindergarten_id = kindergarten.id;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedDate = String.valueOf(resultSet.getDate(3));

            // Формируем строку с информацией
            StringBuilder info = new StringBuilder();
            info.append("Имя: ").append(resultSet.getString(1)).append("\n");
            info.append("Фамилия: ").append(resultSet.getString(2)).append("\n");
            info.append("Дата рождения: ").append(formattedDate).append("\n");
            info.append("Название:").append(resultSet.getString(4)).append('\n');
            info.append("Локация:").append(resultSet.getString(5)).append('\n');
            System.out.println(info);

        }

    }
    public int getKindergartenPrimaryKey(String kindergartenIdentifier) throws SQLException {
        PreparedStatement preparedStatement =  connector.
                prepareStatement("SELECT id FROM kindergarten WHERE identifier = ?");

        preparedStatement.setString(1,kindergartenIdentifier);
        ResultSet resultSet = preparedStatement.executeQuery();
        int IDFromDB = 0;
        while (resultSet.next()){
            IDFromDB = resultSet.getInt("id");
        }
        return IDFromDB;
    }
    public Kindergarten getKindergartenByID(String ID) throws SQLException {
        PreparedStatement preparedStatement =  connector.
                prepareStatement("SELECT * FROM kindergarten WHERE id = ?");

        preparedStatement.setString(1,ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        Kindergarten kindergarten = null;
        while (resultSet.next()){
            kindergarten = new Kindergarten(resultSet.getString("name"),resultSet.getString("location"));
        }
        return kindergarten;
    }

}
