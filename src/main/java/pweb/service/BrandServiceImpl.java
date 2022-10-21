package pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pweb.dto.BrandDto;

@Service
public class BrandServiceImpl implements BrandService{



    @Override
    public List<BrandDto> getBrands() {


        List<BrandDto> brands = new ArrayList<>();
        brands.add(new BrandDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Mercedes", 5, "Gasolina", 45, "47856985245", false));
        brands.add(new BrandDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Audi", 5, "Gasolina", 35, "69852147856", false));


        return brands;
    }

    @Override
    public BrandDto getBrandById(String brandId) {
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
    public void deleteBrand(String id) {
        // TODO Auto-generated method stub

    }


}
