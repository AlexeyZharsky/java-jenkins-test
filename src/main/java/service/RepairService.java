package service;

import model.Car;
import model.Customer;
import model.RepairOrder;
import repository.IRepairRepository;

public class RepairService {
    private final IRepairRepository repository;
    private int nextOrderId = 1;

    public RepairService(IRepairRepository repository) {
        this.repository = repository;
    }

    public RepairOrder createOrder(Customer customer, Car car, String description) {
        RepairOrder order = new RepairOrder(nextOrderId++, customer, car, description);
        System.out.println("[Service] Создание заказа №" + order.getId() +
                " для автомобиля " + car.getRegistrationNumber() +
                ": " + description);
        repository.save(order);
        return order;
    }

    public void changeStatus(int orderId, RepairOrder.Status newStatus) {
        RepairOrder order = getRequiredOrder(orderId);
        System.out.println("[Service] Изменение статуса заказа №" + orderId +
                ": " + order.getStatus() + " -> " + newStatus);
        if (newStatus == RepairOrder.Status.COMPLETED && !order.isRepairPerformed()) {
            System.out.println("[Service] Операция отклонена: ремонт ещё не выполнен");
            throw new IllegalStateException("Нельзя завершить ремонт до его выполнения");
        }
        order.setStatus(newStatus);
        repository.save(order);
        System.out.println("[Service] Статус заказа №" + orderId + " изменён");
    }

    public void markRepairPerformed(int orderId) {
        RepairOrder order = getRequiredOrder(orderId);
        System.out.println("[Service] Отметка выполнения ремонта для заказа №" + orderId);
        order.setRepairPerformed(true);
        repository.save(order);
        System.out.println("[Service] Ремонт по заказу №" + orderId + " выполнен");
    }

    public RepairOrder findById(int orderId) {
        System.out.println("[Service] Получение заказа №" + orderId);
        return getRequiredOrder(orderId);
    }

    private RepairOrder getRequiredOrder(int orderId) {
        RepairOrder order = repository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Заказ не найден: " + orderId);
        }
        return order;
    }
}
