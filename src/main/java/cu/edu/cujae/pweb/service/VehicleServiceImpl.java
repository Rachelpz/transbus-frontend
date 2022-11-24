package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Override
    public List<VehicleDto> getVehicles() {

        List<VehicleDto> vehicles = new ArrayList<>();
        vehicles.add(new VehicleDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "JKU00AS", 5.5F, "Marcelo", "Manolo", "af", false));
        vehicles.add(new VehicleDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "LKA88SA", 8.5F, "Pablo", "Gustavo", "ff", false));


        return vehicles;
    }

    @Override
    public VehicleDto getVehicleById(Integer vehicleId) {
        return getVehicles().stream().filter(r -> r.getVehicle_id().equals(vehicleId)).findFirst().get();
    }

    @Override
    public void createVehicle(VehicleDto vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateVehicle(VehicleDto vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteVehicle(Integer id) {
        // TODO Auto-generated method stub

    }


}
