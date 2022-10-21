package cu.edu.cujae.pweb.service;


import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.BrandDto;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService{



    @Override
    public List<BrandDto> getBrands() {


        List<BrandDto> brands = new ArrayList<>();
        brands.add(new BrandDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Mercedes", 5, "Gasolina", 45, "af", false));
        brands.add(new BrandDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Audi", 8, "Gasolina", 35, "ff", false));


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
