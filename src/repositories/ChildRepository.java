package repositories;

import entity.Child;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChildRepository {
    private final Connection connector; // в начале получаем connection для работы с базой данных

    public ChildRepository(Connection connection) {
        this.connector = connection;
    }


    public Child getChildById(int id) throws SQLException {
        Statement statement = connector.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM children WHERE id = " + id);
        // не стоит использовать обычные statement, т.к них могут быть sql инъекции => всегда стоит
        // использовать подготовленные выражения

        Child child = new Child();
        while (resultSet.next()) {
            child.setName(resultSet.getString("name"));
            child.setAge(resultSet.getInt("age"));
            child.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        }


        return child;

    }


    public List<Child> getAllChild() throws SQLException {
        List<Child> childList = new ArrayList<>();
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM children");
        ResultSet resultSet = preparedStatement.executeQuery();
        // видим как нужно использовать запросы
        while (resultSet.next()) {
            Child child = new Child(); // каждый раз создаём новый объект и добавляем его в список
            child.setName(resultSet.getString("name"));
            child.setAge(resultSet.getInt("age"));
            child.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            childList.add(child);
        }

        return childList;
    }


    public void addChildToDB(Child child) throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("INSERT INTO children(name, age,date_of_birth) VALUES (?,?,?)");

        preparedStatement.setString(1, child.getName());
        preparedStatement.setInt(2,child.getAge());
        preparedStatement.setDate(3, Date.valueOf(child.getDateOfBirth()));


        preparedStatement.executeUpdate();
    }


    public void deleteChildByIndex(int id) throws SQLException {
        PreparedStatement preparedStatement = connector.prepareStatement("DELETE FROM children WHERE id = " + id);
        preparedStatement.executeUpdate();
    }

}
