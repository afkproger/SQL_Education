package repositories;

import entity.Child;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//
public class ChildRepository {
    private final Connection connector; // в начале получаем connection для работы с базой данных

    public ChildRepository(Connection connection) {
        this.connector = connection;
    }

    public void updateInfoAboutChild(Child child , String ID ) throws SQLException {
        //TODO: Нужно заменить обращение по индексу в базе данных , на генерацию идентификатора
        PreparedStatement preparedStatement = connector.
                prepareStatement("UPDATE children SET name = ? , age = ? ,surname = ?, dateOfBirth = ? WHERE identifier = ?" );
        preparedStatement.setString(1 , child.getName());
        preparedStatement.setString(2, child.getSurname());
        preparedStatement.setInt(3 , child.getAge());
        preparedStatement.setDate(4 , Date.valueOf(child.getDateOfBirth()));
        preparedStatement.setString(5 , ID);
    }

    public Child getChildById(String ID) throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("SELECT name , surname , age, date_of_birth FROM children WHERE identifier = ?");
        preparedStatement.setString(1,ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        // не стоит использовать обычные statement, т.к них могут быть sql инъекции => всегда стоит
        // использовать подготовленные выражения

        Child child = new Child();
        while (resultSet.next()) {
            child.setName(resultSet.getString("name"));
            child.setSurname(resultSet.getString("surname"));
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
            child.setSurname(resultSet.getString("surname"));
            child.setAge(resultSet.getInt("age"));
            child.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            childList.add(child);
        }

        return childList;
    }


    public void addChildToDB(Child child , String ID , String kindergartenID) throws SQLException {
        PreparedStatement preparedStatement = connector.
                prepareStatement("INSERT INTO children(identifier ,name,surname,age,date_of_birth,fk_kindergarten_id) VALUES (?,?,?,?,?,?)");
        preparedStatement.setString(1,ID);
        preparedStatement.setString(2, child.getName());
        preparedStatement.setString(3, child.getSurname());
        preparedStatement.setInt(4,child.getAge());
        preparedStatement.setDate(5, Date.valueOf(child.getDateOfBirth()));
        preparedStatement.setString(6,kindergartenID);


        preparedStatement.executeUpdate();
    }
    private int getChildPrimaryKey(String childIdentifier) throws SQLException {
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT id FROM children WHERE identifier = ?");
        preparedStatement.setString(1,childIdentifier);
        int id = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            id = resultSet.getInt("id");
        }
        return id;
    }

    public void deleteChildByIndex(String ID) throws SQLException {
        int id = getChildPrimaryKey(ID);
        PreparedStatement preparedStatement = connector.prepareStatement("DELETE FROM children WHERE id = ?");
        preparedStatement.setString(1,String.valueOf(id));
        preparedStatement.executeUpdate();
    }

}
