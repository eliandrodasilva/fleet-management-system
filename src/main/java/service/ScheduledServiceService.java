package service;

import dao.ScheduledServiceDAO;
import model.ScheduledService;
import model.enums.ServiceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduledServiceService {

    private final ScheduledServiceDAO scheduledServiceDAO = new ScheduledServiceDAO();

    public void createScheduledService(ScheduledService service) {
        validateService(service);
        scheduledServiceDAO.save(service);
    }

    public ScheduledService updateScheduledService(ScheduledService service) {
        ScheduledService existing = scheduledServiceDAO.findById(service.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Serviço agendado não encontrado.");
        }

        validateUpdate(existing, service);

        return scheduledServiceDAO.update(service);
    }

    public void deleteScheduledService(Long id) {
        ScheduledService service = scheduledServiceDAO.findById(id);
        if (service != null) {
            scheduledServiceDAO.delete(service);
        }
    }

    public ScheduledService getById(Long id) {
        return scheduledServiceDAO.findById(id);
    }

    public List<ScheduledService> getByStatus(ServiceStatus status) {
        return scheduledServiceDAO.findByStatus(status);
    }

    public List<ScheduledService> getAll() {
        return scheduledServiceDAO.findAll();
    }

    private void validateService(ScheduledService service) {
        if (service.getVehicle() == null) {
            throw new IllegalArgumentException("O veículo é obrigatório para o agendamento.");
        }
        if (service.getScheduledDate() == null || service.getScheduledDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data agendada não pode ser no passado.");
        }
        if (service.getEstimatedCost() == null || service.getEstimatedCost().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O custo estimado deve ser positivo.");
        }
    }

    private void validateUpdate(ScheduledService existing, ScheduledService updated) {
        validateService(updated);

        // Impedir alterações de status inválidas
        if (existing.getServiceStatus() == ServiceStatus.CANCELED &&
                updated.getServiceStatus() == ServiceStatus.COMPLETED) {
            throw new IllegalArgumentException("Não é possível concluir um serviço que foi cancelado.");
        }

        // Custo estimado não pode ser negativo
        if (updated.getEstimatedCost().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O custo estimado não pode ser negativo.");
        }
    }
}
