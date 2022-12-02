package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.CountryDto;
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
public class CountryServiceImpl implements CountryService {

    @Autowired
    private RestService restService;

    @Override
    public List<CountryDto> getCountries() {

        List<CountryDto> countries = new ArrayList<CountryDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CountryDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/countries/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            countries = apiRestMapper.mapList(response, CountryDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public CountryDto getCountryById(Integer countryId) {
        CountryDto country = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CountryDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/countries/{countryId}");
            String uri = template.expand(countryId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            country = apiRestMapper.mapOne(response, CountryDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return country;
    }
}
