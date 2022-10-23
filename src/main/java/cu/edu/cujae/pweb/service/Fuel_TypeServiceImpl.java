package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.FuelDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Fuel_TypeServiceImpl implements Fuel_TypeService{
    @Override
    public List<FuelDto> getFuels() {
        List<FuelDto> fuels = new ArrayList<>();
        fuels.add(new FuelDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Gasolina", false));
        fuels.add(new FuelDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Petróleo",  false));


        return fuels;
    }

    @Override
    public FuelDto getFuelById(String FuelId) {
        return getFuels().stream().filter(r -> r.getFuel_id().equals(FuelId)).findFirst().get();
    }

    @Override
    public void createFuel(FuelDto fuel) {

    }

    @Override
    public void updateFuel(FuelDto fuel) {

    }

    @Override
    public void deleteFuel(String id) {

    }
}
