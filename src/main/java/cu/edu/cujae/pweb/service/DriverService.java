package cu.edu.cujae.pweb.service;




import cu.edu.cujae.pweb.dto.DriverDto;

import java.util.List;

public interface DriverService {
    List<DriverDto> getDrivers();
    DriverDto getDriverById(String driverId);
    void createDriver(DriverDto dirver);
    void updateDriver(DriverDto driver);
    void deleteDriver(String id);
}
