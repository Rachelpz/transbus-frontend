package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.VehicleDto;
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
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private RestService restService;

    @Override
    public List<VehicleDto> getVehicles() {

        List<VehicleDto> vehicles = new ArrayList<VehicleDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<VehicleDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/vehicles/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            vehicles = apiRestMapper.mapList(response, VehicleDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public VehicleDto getVehicleById(Integer vehicleId) {
        VehicleDto vehicle = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<VehicleDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/vehicles/{vehicleId}");
            String uri = template.expand(vehicleId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            vehicle = apiRestMapper.mapOne(response, VehicleDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return vehicle;
    }

    @Override
    public void createVehicle(VehicleDto vehicle) {
        restService.POST("/api/v1/vehicles/", vehicle, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateVehicle(VehicleDto vehicle) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/vehicles/", params, vehicle, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteVehicle(Integer vehicleId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/vehicles/{vehicleId}");
        String uri = template.expand(vehicleId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }
}
