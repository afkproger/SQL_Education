package controllers;

import entity.Kindergarten;
import repositories.KindergartenRepository;
import util.UserInteractionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class KindergartenController {
    public static int getPrimaryKey(Connection connection , String ID) throws SQLException {
        KindergartenRepository kindergartenRepository = new KindergartenRepository(connection);
        return kindergartenRepository.getKindergartenPrimaryKey(ID);
    }
    private Kindergarten makeNewKindergarten(){
        String name = getKindergartenName();
        String location  = getKindergartenLocation();
        return new Kindergarten(name,location);
    }
    public List<Kindergarten> getAllKindergartens(Connection connection) throws SQLException {
        KindergartenRepository kindergartenRepository = new KindergartenRepository(connection);
        return kindergartenRepository.getAllKindergartens();
    }
    public Kindergarten getKindergartenByID(Connection connection , String ID) throws SQLException {
        KindergartenRepository kindergartenRepository = new KindergartenRepository(connection);
        return kindergartenRepository.getKindergartenByID(ID);
    }

    public void makeNewKindergartenAndAddToDB(Connection connection , String ID) throws SQLException {
        Kindergarten kindergarten = makeNewKindergarten();
        System.out.println(kindergarten);
        KindergartenRepository kindergartenRepository = new KindergartenRepository(connection);
        kindergartenRepository.addNewKindergarten(kindergarten,ID);
    }

    private String getKindergartenName(){
        System.out.println("Введите название детсада");
        return UserInteractionManager.consoleInput.nextLine();
    }

    private String getKindergartenLocation(){
        System.out.println("Введите расположение детсада");
        return UserInteractionManager.consoleInput.nextLine();
    }
}
