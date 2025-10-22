package dao;

import model.ScheduledService;
import model.enums.ServiceStatus;
import model.enums.ServiceType;

import java.util.List;

public class ScheduledServiceDAO extends AbstractDAOImpl<ScheduledService, Long> {

    public ScheduledServiceDAO() {}

    public List<ScheduledService> findByStatus(ServiceStatus status) {
        return em.createQuery(
                        "FROM ScheduledService s WHERE s.serviceStatus = :status", ScheduledService.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<ScheduledService> findByServiceType(ServiceType type) {
        return em.createQuery(
                        "FROM ScheduledService s WHERE s.serviceType = :type", ScheduledService.class)
                .setParameter("type", type)
                .getResultList();
    }
}
