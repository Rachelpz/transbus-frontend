package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    List<ServiceDto> getServices();
    ServiceDto getServiceById(Integer serviceId);
    void createService(ServiceDto service);
    void updateService(ServiceDto service);
    void deleteService(Integer id);
}
