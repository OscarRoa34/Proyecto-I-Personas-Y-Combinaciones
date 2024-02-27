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

    boolean startsWith(String nombre) {
        throw new UnsupportedOperationException("Unimplemented method 'startsWith'");
    }

    String substring(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'substring'");
    }
}
