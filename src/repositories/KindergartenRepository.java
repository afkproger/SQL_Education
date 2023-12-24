package repositories;

import entity.Kindergarten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Kindergarten> getAllKindergartens() throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("SELECT name , location FROM kindergarten");
        List<Kindergarten> kindergartens = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            kindergartens.add(
                    new Kindergarten(resultSet.getString("name"),resultSet.getString("location")));

        }

        return kindergartens;
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
