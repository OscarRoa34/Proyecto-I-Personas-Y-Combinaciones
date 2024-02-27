package co.edu.uptc.models;

public enum Gender {
    MALE("Masculino"),
    FEMALE("Femenino");

    private final String representacion;

    Gender(String representacion) {
        this.representacion = representacion;
    }

    public String getRepresentacion() {
        return representacion;
    }
}
