package repositories;

import entity.Kindergarten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KindergartenRepository {
    private final Connection connector ;

    public KindergartenRepository(Connection connector) {
        this.connector = connector;
    }

    public void addNewKindergarten(Kindergarten kindergarten) throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("INSERT INTO kindergarten (name, location) VALUES(?,?");
        preparedStatement.setString(1, kindergarten.getName());
        preparedStatement.setString(2, kindergarten.getLocation());
    }

}
