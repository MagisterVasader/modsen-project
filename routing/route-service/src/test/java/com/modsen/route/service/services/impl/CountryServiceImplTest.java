package com.modsen.route.service.services.impl;

import com.modsen.route.service.model.Country;
import com.modsen.route.service.repositories.CountryRepository;
import com.modsen.route.service.services.exceptions.CountryAlreadyExistsException;
import com.modsen.route.service.services.exceptions.CountryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    public void testGetListOfCountries() {
        // Given
        List<Country> expectedCountries = Arrays.asList(
                new Country("A", "Aa", 1., 1., new HashSet<>()),
                new Country("B", "Bb", 2., 2., new HashSet<>()),
                new Country("C", "Cc", 3., 3., new HashSet<>())
        );
        when(countryRepository.findAll()).thenReturn(expectedCountries);

        // When
        List<Country> actualCountries = countryService.getCountries();

        // Then
        verify(countryRepository).findAll();
        assertEquals(expectedCountries, actualCountries);
    }

    @Test
    public void testGetEmptyListOfCountries() {
        // Given
        when(countryRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Country> actualCountries = countryService.getCountries();

        // Then
        verify(countryRepository).findAll();
        assertEquals(Collections.emptyList(), actualCountries);
    }

    @Test
    public void testGetCountrySuccess() {
        // Given
        Country testCountry = new Country("A", "Aa", 1., 1., new HashSet<>());
        when(countryRepository.findById("A")).thenReturn(Optional.of(testCountry));

        // When
        Country result = countryService.getCountry("A");

        // Then
        verify(countryRepository).findById("A");
        assertEquals(testCountry, result);
    }

    @Test
    public void testGetCountryNotFound() {
        // Given
        when(countryRepository.findById("XX")).thenReturn(Optional.empty());

        // When, Then
        assertThrows(CountryNotFoundException.class, () -> countryService.getCountry("XX"));
        verify(countryRepository).findById("XX");
    }

    @Test
    public void testCreateCountrySuccess() {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        when(countryRepository.existsById("A")).thenReturn(false);
        when(countryRepository.save(country)).thenReturn(country);

        // When
        Country createdCountry = countryService.createCountry(country);

        // Then
        verify(countryRepository).save(country);
        assertEquals(country, createdCountry);
    }

    @Test
    public void testCreateCountryAlreadyExists() {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        when(countryRepository.existsById("A")).thenReturn(true);

        // When, Then
        assertThrows(CountryAlreadyExistsException.class, () -> countryService.createCountry(country));
    }

    @Test
    public void testUpdateCountry() {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        when(countryRepository.existsById("A")).thenReturn(true);
        when(countryRepository.save(country)).thenReturn(country);

        // When
        Country updatedCountry = countryService.updateCountry(country);

        // Then
        verify(countryRepository).save(country);
        assertEquals(country, updatedCountry);
    }

    @Test
    public void testUpdateCountryNotFound() {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        when(countryRepository.existsById("A")).thenReturn(false);

        // When, Then
        assertThrows(CountryNotFoundException.class, () -> countryService.updateCountry(country));
    }

    @Test
    public void testDeleteCountrySuccess() {
        // Given
        when(countryRepository.existsById("A")).thenReturn(true);
        doNothing().when(countryRepository).deleteById("A");

        // When
        countryService.deleteCountry("A");

        // Then
        verify(countryRepository).deleteById("A");
    }

    @Test
    public void testDeleteCountryWithNonExistentCountry() {
        // Given
        when(countryRepository.existsById("A")).thenReturn(false);

        // When, Then
        assertThrows(CountryNotFoundException.class, () -> countryService.deleteCountry("A"));
    }
}
