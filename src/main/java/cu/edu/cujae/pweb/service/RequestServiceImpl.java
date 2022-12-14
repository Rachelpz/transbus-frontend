package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.RequestDto;
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
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RestService restService;

    @Override
    public List<RequestDto> getRequests() {

        List<RequestDto> requests = new ArrayList<RequestDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RequestDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/requests/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            requests = apiRestMapper.mapList(response, RequestDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public RequestDto getRequestById(Integer requestId) {
        RequestDto request = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RequestDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/requests/{requestId}");
            String uri = template.expand(requestId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            request = apiRestMapper.mapOne(response, RequestDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return request;
    }

    @Override
    public void createRequest(RequestDto request) {
        restService.POST("/api/v1/requests/", request, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateRequest(RequestDto request) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/requests/", params, request, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public boolean deleteRequest(Integer requestId) {
        boolean status=true;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/requests/{requestId}");
        String uri = template.expand(requestId).toString();

        if(restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getStatusCode().isError()){
            return status=false;
        }
        else{
            restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            return status ;
        }
    }
}
