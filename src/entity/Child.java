package entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Child {
    private String name;
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private int age;
    private LocalDate dateOfBirth;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = dateOfBirth.format(dateFormatter);

        // Формируем строку с информацией
        StringBuilder info = new StringBuilder();
        info.append("Имя: ").append(name).append("\n");
        info.append("Фамилия: ").append(surname).append("\n");
        info.append("Возраст: ").append(age).append("\n");
        info.append("Дата рождения: ").append(formattedDate).append("\n");

        return info.toString();
    }

    public Child(){};
    public Child(String name,String surname, int age, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
