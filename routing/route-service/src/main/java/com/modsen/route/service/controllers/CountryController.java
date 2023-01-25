package com.modsen.route.service.controllers;

import com.modsen.route.service.model.Country;
import com.modsen.route.service.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routing/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        LOGGER.info("[RT] Getting countries");
        var countries = countryService.getCountries();
        LOGGER.info("[RT] Found countries: " + countries);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        LOGGER.info("[RT] Getting country by code " + code);
        var country = countryService.getCountry(code);
        LOGGER.info("[RT] Found country: " + country);
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        LOGGER.info("[RT] Creating country");
        var created = countryService.createCountry(country);
        LOGGER.info("[RT] Created country: " + created);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        LOGGER.info("[RT] Updating country");
        var updated = countryService.updateCountry(country);
        LOGGER.info("[RT] Updated country: " + updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String code) {
        LOGGER.info("[RT] Deleting country by code: " + code);
        countryService.deleteCountry(code);
        LOGGER.info("[RT] Country deleted by code: " + code);
        return ResponseEntity.noContent().build();
    }
}
