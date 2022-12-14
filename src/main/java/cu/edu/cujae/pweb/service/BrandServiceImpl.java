package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
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
public class BrandServiceImpl implements BrandService {


    @Autowired
    private RestService restService;

    @Override
    public List<BrandDto> getBrands() {

        List<BrandDto> brands = new ArrayList<BrandDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/brands/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            brands = apiRestMapper.mapList(response, BrandDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return brands;

    }
    @Override
    public Integer getBrandsSize() {

        List<BrandDto> brands = new ArrayList<BrandDto>();
        Integer size=0;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/brands/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            brands = apiRestMapper.mapList(response, BrandDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size=brands.size();

    }

    @Override
    public BrandDto getBrandById(Integer brandId) {
        BrandDto brand = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/brands/{brandId}");
            String uri = template.expand(brandId).toString();
            String response = (String) restService.GET(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
            brand = apiRestMapper.mapOne(response, BrandDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return brand;
    }

    @Override
    public void createBrand(BrandDto brand) {
        restService.POST("/api/v1/brands/", brand, String.class,CurrentUserUtils.getTokenBearer()).getBody();

    }

    @Override
    public void updateBrand(BrandDto brand) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/brands/", params, brand, String.class,CurrentUserUtils.getTokenBearer()).getBody();

    }

    @Override
    public boolean deleteBrand(Integer brandId) {
        boolean status=true;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/brands/{brandId}");
        String uri = template.expand(brandId).toString();

        if(restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getStatusCode().isError()){
            return status=false;
        }
        else{
            restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            return status ;
        }
    }


}
