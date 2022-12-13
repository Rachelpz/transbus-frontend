package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DriverDto;

import java.util.List;

public interface DriverService {
    List<DriverDto> getDrivers();
    DriverDto getDriverById(Integer driverId);
    void createDriver(DriverDto dirver);
    void updateDriver(DriverDto driver);
    boolean deleteDriver(Integer id);
    Integer getDriversSize();
}
