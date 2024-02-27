package co.edu.uptc.models;

public class NameAndGender {
    private String name;
    private Gender gender;

    public NameAndGender(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
}
