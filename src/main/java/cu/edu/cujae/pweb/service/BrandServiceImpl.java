package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.BrandDto;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{


    @Autowired
    private RestService restService;

    @Override
    public List<BrandDto> getBrands() {

        List<BrandDto> brands = new ArrayList<BrandDto>();


        return brands;

    }

    @Override
    public BrandDto getBrandById(Integer brandId) {
        return getBrands().stream().filter(r -> r.getBrand_id().equals(brandId)).findFirst().get();
    }

    @Override
    public void createBrand(BrandDto brand) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBrand(BrandDto brand) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteBrand(Integer id) {
        // TODO Auto-generated method stub

    }

}
