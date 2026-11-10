package model;

public class Car {
    private final String registrationNumber;
    private final String model;
    private final int productionYear;

    public Car(String registrationNumber, String model, int productionYear) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.productionYear = productionYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }
}
