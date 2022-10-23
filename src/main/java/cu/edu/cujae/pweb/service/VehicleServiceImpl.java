package cu.edu.cujae.pweb.service;


import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.VehicleDto;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService{



    @Override
    public List<VehicleDto> getVehicles() {


        List<VehicleDto> brands = new ArrayList<>();
        brands.add(new VehicleDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "JKU00AS", 5.5F, "Marcelo", "Manolo", "af", false));
        brands.add(new VehicleDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "LKA88SA", 8.5F, "Pablo", "Gustavo", "ff", false));


        return brands;
    }

    @Override
    public VehicleDto getVehicleById(String vehicleId) {
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
    public void deleteVehicle(String id) {
        // TODO Auto-generated method stub

    }


}
