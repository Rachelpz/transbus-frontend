package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<CountryDto> getCountries();
    CountryDto getCountryById(Integer CountryId);
}
