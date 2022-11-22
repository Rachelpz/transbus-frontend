package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.FuelDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Fuel_TypeServiceImpl implements Fuel_TypeService{

    @Autowired
    private RestService restService;

    @Override
    public List<FuelDto> getFuels() {
        List<FuelDto> fuels = new ArrayList<>();

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<FuelDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/fuels/", params, String.class).getBody();
            fuels = apiRestMapper.mapList(response, FuelDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fuels;
    }

    @Override
    public FuelDto getFuelById(Integer FuelId) {
        return getFuels().stream().filter(r -> r.getFuel_id().equals(FuelId)).findFirst().get();
    }

    @Override
    public void createFuel(FuelDto fuel) {

    }

    @Override
    public void updateFuel(FuelDto fuel) {

    }

    @Override
    public void deleteFuel(Integer id) {

    }
}
