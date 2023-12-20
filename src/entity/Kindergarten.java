package entity;

public class Kindergarten {
    private String name;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Kindergarten(String name, String location) {
        this.name = name;
        this.location = location;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название:").append(name).append('\n');
        stringBuilder.append("Локация:").append(location).append('\n');
        return stringBuilder.toString();
    }
}
