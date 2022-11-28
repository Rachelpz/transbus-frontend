package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.ServiceDto;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Override
    public List<ServiceDto> getServices() {

        List<ServiceDto> services = new ArrayList<>();
        services.add(new ServiceDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Service 1", "AAA", Time.valueOf(LocalTime.now()), 1.5F, 5F, "Servicio 1", false ));
        services.add(new ServiceDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Service 2", "BBB", Time.valueOf(LocalTime.now()), 1.6F, 8F, "Servicio 2", false ));

        return services;
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) {
        return getServices().stream().filter(r -> r.getService_id().equals(serviceId)).findFirst().get();
    }

    @Override
    public void createService(ServiceDto service) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateService(ServiceDto service) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteService(Integer id) {
        // TODO Auto-generated method stub

    }

}
