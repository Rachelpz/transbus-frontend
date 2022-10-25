package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DistrictDto;
import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.BrandDto;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Override
    public List<DistrictDto> getDistricts() {

        List<DistrictDto> districts = new ArrayList<>();
        districts.add(new DistrictDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Distrito 1", false));
        districts.add(new DistrictDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Distrito 2", false));
        districts.add(new DistrictDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Distrito 3", false));

        return districts;
    }

    @Override
    public DistrictDto getDistrictById(String districtId) {
        return getDistricts().stream().filter(r -> r.getDistrict_id().equals(districtId)).findFirst().get();
    }

    @Override
    public void createDistrict(DistrictDto district) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDistrict(DistrictDto district) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteDistrict(String id) {
        // TODO Auto-generated method stub

    }


}
