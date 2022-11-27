package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.DriverDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService{

    @Override
    public List<DriverDto> getDrivers() {

        List<DriverDto> drivers = new ArrayList<>();
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 1", "00021060502", "KSA", "55654413", "Distrito 1", "Marca 1", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 2", "98021235102", "KSA", "55673513", "Distrito 2", "Marca 2", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 3", "00012487635", "KSA", "55673513", "Distrito 2", "Marca 3", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 4", "03051145698", "KSA", "55673513", "Distrito 2", "Marca 4", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 5", "00071966627", "KSA", "55673513", "Distrito 2", "Marca 5", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 6", "95072021365", "KSA", "55673513", "Distrito 2", "Marca 6", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 7", "96021835103", "KSA", "55673513", "Distrito 2", "Marca 7", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 8", "91021935102", "KSA", "55673513", "Distrito 2", "Marca 8", false ));
        drivers.add(new DriverDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Driver 9", "90022735129", "KSA", "55673513", "Distrito 2", "Marca 9", false ));

        return drivers;
    }

    @Override
    public DriverDto getDriverById(Integer driverId) {
        return getDrivers().stream().filter(r -> r.getDriver_id().equals(driverId)).findFirst().get();
    }

    @Override
    public void createDriver(DriverDto driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDriver(DriverDto driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteDriver(Integer id) {
        // TODO Auto-generated method stub

    }

}
