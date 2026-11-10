import model.Car;
import model.Customer;
import model.RepairOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.InMemoryRepairRepository;
import service.RepairService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepairServiceTest {
    private RepairService service;

    @BeforeEach
    void setUp() {
        InMemoryRepairRepository repository = new InMemoryRepairRepository();
        service = new RepairService(repository);
    }

    @Test
    @DisplayName("Успешное завершение выполненного ремонта")
    void shouldCompletePerformedRepair() {
        RepairOrder order = service.createOrder(customer(), car(), "Диагностика");

        service.markRepairPerformed(order.getId());
        service.changeStatus(order.getId(), RepairOrder.Status.COMPLETED);

        assertEquals(RepairOrder.Status.COMPLETED, order.getStatus());
    }

    @Test
    @DisplayName("Запрет завершения ремонта до его выполнения")
    void shouldRejectCompletionBeforeRepair() {
        RepairOrder order = service.createOrder(customer(), car(), "Замена масла");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.changeStatus(order.getId(), RepairOrder.Status.COMPLETED)
        );

        assertEquals("Нельзя завершить ремонт до его выполнения", exception.getMessage());
        assertEquals(RepairOrder.Status.CREATED, order.getStatus());
    }

    @Test
    @DisplayName("Изменение статуса заказа на в работе")
    void shouldChangeStatusToInProgress() {
        RepairOrder order = service.createOrder(customer(), car(), "Ремонт подвески");

        service.changeStatus(order.getId(), RepairOrder.Status.IN_PROGRESS);

        assertEquals(RepairOrder.Status.IN_PROGRESS, order.getStatus());
    }

    private Customer customer() {
        return new Customer(1, "Тестовый клиент");
    }

    private Car car() {
        return new Car("T000TT", "Lada Vesta", 2022);
    }
}
