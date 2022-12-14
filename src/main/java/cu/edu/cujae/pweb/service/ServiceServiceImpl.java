package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.ServiceDto;
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
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private RestService restService;

    @Override
    public List<ServiceDto> getServices() {

        List<ServiceDto> services = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ServiceDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/services/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            services = apiRestMapper.mapList(response, ServiceDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) {
        ServiceDto service = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ServiceDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/services/{serviceId}");
            String uri = template.expand(serviceId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            service = apiRestMapper.mapOne(response, ServiceDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return service;
    }

    @Override
    public void createService(ServiceDto service) {
        restService.POST("/api/v1/services/", service, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateService(ServiceDto service) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/services/", params, service, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public boolean deleteService(Integer serviceId) {
        boolean status=true;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/services/{serviceId}");
        String uri = template.expand(serviceId).toString();
        if(restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getStatusCode().isError()){
            return status=false;
        }
        else{
            restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            return status ;
        }

    }
}
