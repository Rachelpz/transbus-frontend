package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DistrictDto;
import java.util.List;

public interface DistrictService {
    List<DistrictDto> getDistricts();
    DistrictDto getDistrictById(Integer districtId);
    void createDistrict(DistrictDto district);
    void updateDistrict(DistrictDto district);
    void deleteDistrict(Integer id);
}
