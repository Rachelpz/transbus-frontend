package cu.edu.cujae.pweb.service;

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
        fuels.add(new FuelDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Gasolina", false));
        fuels.add(new FuelDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Petróleo",  false));


        return fuels;
    }

    @Override
    public FuelDto getFuelById(Integer FuelId) {
        return getFuels().stream().filter(r -> r.getFuel_id().equals(FuelId)).findFirst().get();
    }

    @Override
    public void createFuel(FuelDto fuel) {

    }

    @Override
    public void updateFuel(FuelDto fuel) {

    }

    @Override
    public void deleteFuel(Integer id) {

    }
}
