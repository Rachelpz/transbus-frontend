package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DistrictDto;
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

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private RestService restService;

    @Override
    public List<DistrictDto> getDistricts() {

        List<DistrictDto> districts = new ArrayList<DistrictDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DistrictDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/districts/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            districts = apiRestMapper.mapList(response, DistrictDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return districts;
    }

    @Override
    public DistrictDto getDistrictById(Integer districtId) {
        DistrictDto district = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DistrictDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/districts/{districtId}");
            String uri = template.expand(districtId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            district = apiRestMapper.mapOne(response, DistrictDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return district;
    }

    @Override
    public List<DriverDto> getDriversByIdDistrict(String districtName) {
        List<DriverDto> allDrivers = new ArrayList<DriverDto>();
        List<DriverDto> drivers = new ArrayList<DriverDto>();

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/drivers/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            allDrivers = apiRestMapper.mapList(response, DriverDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DriverDto allDriver : allDrivers) {
            if (allDriver.getDistrict().getDistrict_name().trim().equals(districtName)) {
                drivers.add(allDriver);
            }
        }

        return drivers;
    }

    @Override
    public void createDistrict(DistrictDto district) {
        restService.POST("/api/v1/districts/", district, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateDistrict(DistrictDto district) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/districts/", params, district, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public boolean deleteDistrict(Integer districtId) {
        boolean status=true;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/districts/{districtId}");
        String uri = template.expand(districtId).toString();
        if(restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getStatusCode().isError()){
            return status=false;
        }
        else{
            restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            return status ;
        }

    }
}
