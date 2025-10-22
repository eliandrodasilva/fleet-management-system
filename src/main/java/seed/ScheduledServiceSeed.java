package seed;

import model.ScheduledService;
import model.Vehicle;
import model.enums.ServicePriority;
import model.enums.ServiceStatus;
import model.enums.ServiceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledServiceSeed {

    public static List<ScheduledService> getScheduledServices(List<Vehicle> vehicles) {
        List<ScheduledService> list = new ArrayList<>();

        if (vehicles == null || vehicles.isEmpty()) return list;

        list.add(new ScheduledService(
                vehicles.get(0),
                ServiceType.OIL,
                LocalDateTime.now().plusDays(3),
                new BigDecimal("1500.00"),
                "Troca de óleo e revisão geral",
                ServicePriority.HIGH,
                ServiceStatus.SCHEDULED
        ));

        list.add(new ScheduledService(
                vehicles.get(1),
                ServiceType.TIRE,
                LocalDateTime.now().plusDays(5),
                new BigDecimal("2300.00"),
                "Substituição de freios e alinhamento",
                ServicePriority.MEDIUM,
                ServiceStatus.SCHEDULED
        ));

        list.add(new ScheduledService(
                vehicles.get(2),
                ServiceType.INSPECTION,
                LocalDateTime.now().plusDays(10),
                new BigDecimal("500.00"),
                "Inspeção semestral de segurança",
                ServicePriority.LOW,
                ServiceStatus.SCHEDULED
        ));

        return list;
    }
}
