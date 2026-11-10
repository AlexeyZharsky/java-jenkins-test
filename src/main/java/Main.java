import model.Car;
import model.Customer;
import model.RepairOrder;
import repository.InMemoryRepairRepository;
import service.RepairService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск приложения автосервиса ===");
        InMemoryRepairRepository repository = new InMemoryRepairRepository();
        RepairService repairService = new RepairService(repository);

        Customer customer = new Customer(1, "Иван Петров");
        Car car = new Car("A123BC", "Toyota Camry", 2020);
        RepairOrder order = repairService.createOrder(customer, car, "Замена тормозных колодок");

        repairService.changeStatus(order.getId(), RepairOrder.Status.IN_PROGRESS);
        repairService.markRepairPerformed(order.getId());
        repairService.changeStatus(order.getId(), RepairOrder.Status.COMPLETED);

        System.out.println("Заказ №" + order.getId() + " завершён: " +
                repairService.findById(order.getId()).getStatus());
        System.out.println("=== Приложение завершило работу успешно ===");
    }
}
