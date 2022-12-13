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
import java.util.UUID;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private RestService restService;

    @Override
    public List<DistrictDto> getDistricts() {

        List<DistrictDto> brands = new ArrayList<DistrictDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DistrictDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/districts/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            brands = apiRestMapper.mapList(response, DistrictDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return brands;
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
    public List<DriverDto> getDriversByIdDistrict(Integer districtId) {
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

        List<DriverDto> brands = new ArrayList<DriverDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/drivers/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            brands = apiRestMapper.mapList(response, DriverDto.class);
            for (DriverDto driver:brands
                 ) {if (!driver.getDistrict().equals(district)){
                     brands.remove(driver);
            }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return brands;
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
    public void deleteDistrict(Integer districtId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/districts/{districtId}");
        String uri = template.expand(districtId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }
}
