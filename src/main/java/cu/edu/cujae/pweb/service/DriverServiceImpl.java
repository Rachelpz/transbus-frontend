package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private RestService restService;

    @Override
    public List<DriverDto> getDrivers() {

        List<DriverDto> brands = new ArrayList<DriverDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/drivers/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            brands = apiRestMapper.mapList(response, DriverDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return brands;
    }

    @Override
    public Integer getDriversSize() {

        List<DriverDto> drivers = new ArrayList<DriverDto>();
        Integer size=0;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/drivers/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            drivers = apiRestMapper.mapList(response, DriverDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size=drivers.size();

    }

    @Override
    public DriverDto getDriverById(Integer driverId) {
        DriverDto district = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/drivers/{driverId}");
            String uri = template.expand(driverId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            district = apiRestMapper.mapOne(response, DriverDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return district;
    }

    @Override
    public void createDriver(DriverDto driver) {
        restService.POST("/api/v1/drivers/", driver, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateDriver(DriverDto driver) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/drivers/", params, driver, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteDriver(Integer driverId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/drivers/{driverId}");
        String uri = template.expand(driverId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }
}
