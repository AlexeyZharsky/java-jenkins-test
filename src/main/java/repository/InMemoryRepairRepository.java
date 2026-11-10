package repository;

import model.RepairOrder;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepairRepository implements IRepairRepository {
    private final Map<Integer, RepairOrder> orders = new HashMap<>();

    @Override
    public void save(RepairOrder order) {
        orders.put(order.getId(), order);
        System.out.println("[Repository] Сохранён заказ №" + order.getId());
    }

    @Override
    public RepairOrder findById(int id) {
        RepairOrder order = orders.get(id);
        System.out.println("[Repository] Поиск заказа №" + id + ": " +
                (order == null ? "не найден" : "найден"));
        return order;
    }
}
