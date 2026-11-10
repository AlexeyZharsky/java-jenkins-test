package repository;

import model.RepairOrder;

public interface IRepairRepository {
    void save(RepairOrder order);

    RepairOrder findById(int id);
}
