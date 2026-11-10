package model;

public class RepairOrder {
    public enum Status {
        CREATED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    private final int id;
    private final Customer customer;
    private final Car car;
    private final String description;
    private Status status;
    private boolean repairPerformed;

    public RepairOrder(int id, Customer customer, Car car, String description) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.description = description;
        this.status = Status.CREATED;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isRepairPerformed() {
        return repairPerformed;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRepairPerformed(boolean repairPerformed) {
        this.repairPerformed = repairPerformed;
    }
}
