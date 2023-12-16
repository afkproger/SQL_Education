package entity;

public class Student {
    private String name;
    private String surname;
    private double avg_grade;
    private String faculty;
    private String institute;

    public Student(String name, String surname, double avg_grade, String faculty, String institute) {
        this.name = name;
        this.surname = surname;
        this.avg_grade = avg_grade;
        this.faculty = faculty;
        this.institute = institute;
    }

    public String getName() { 
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(double avg_grade) {
        this.avg_grade = avg_grade;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
