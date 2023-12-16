package entity;

public class Institute {
    private String name;
    private int count_of_students;

    @Override
    public String toString() {
        return "entity.Institute{" +
                "name='" + name + '\'' +
                ", count_of_students=" + count_of_students +
                '}';
    }

    public Institute(String name, int count_of_students) {
        this.name = name;
        this.count_of_students = count_of_students;
    }
}
