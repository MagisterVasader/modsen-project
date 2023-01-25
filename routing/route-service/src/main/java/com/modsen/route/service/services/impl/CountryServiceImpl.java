package com.modsen.route.service.services.impl;

import com.modsen.route.service.model.Country;
import com.modsen.route.service.repositories.CountryRepository;
import com.modsen.route.service.services.CountryService;
import com.modsen.route.service.services.exceptions.CountryAlreadyExistsException;
import com.modsen.route.service.services.exceptions.CountryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountry(String code) {
        return countryRepository.findById(code)
                .orElseThrow(() -> new CountryNotFoundException(code));
    }

    @Override
    public Country createCountry(Country country) {
        if (countryRepository.existsById(country.getCode())) {
            throw new CountryAlreadyExistsException(country.getCode());
        }
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        if (countryRepository.existsById(country.getCode())) {
            return countryRepository.save(country);
        }
        throw new CountryNotFoundException(country.getCode());
    }

    @Override
    public void deleteCountry(String code) {
        if (countryRepository.existsById(code)) {
            countryRepository.deleteById(code);
        } else {
            throw new CountryNotFoundException(code);
        }
    }
}
