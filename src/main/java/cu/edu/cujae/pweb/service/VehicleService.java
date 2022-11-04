package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    List<VehicleDto> getVehicles();
    VehicleDto getVehicleById(Integer VehicleId);
    void createVehicle(VehicleDto vehicle);
    void updateVehicle(VehicleDto vehicle);
    void deleteVehicle(Integer id);
}
