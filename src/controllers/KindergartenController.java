package controllers;

import entity.Kindergarten;
import repositories.KindergartenRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//TODO:Дописать контроллер для сада и думать как это дальше связать
public class KindergartenController {
    private final Scanner consoleInput = new Scanner(System.in);
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
        KindergartenRepository kindergartenRepository = new KindergartenRepository(connection);
        kindergartenRepository.addNewKindergarten(kindergarten,ID);
    }

    private String getKindergartenName(){
        System.out.println("Введите название детсада");
        return consoleInput.nextLine();
    }

    private String getKindergartenLocation(){
        System.out.println("Введите расположение детсада");
        return consoleInput.nextLine();
    }
}
