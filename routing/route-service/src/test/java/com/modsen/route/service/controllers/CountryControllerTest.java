package com.modsen.route.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.route.service.model.Country;
import com.modsen.route.service.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    public void testGetCountries() throws Exception {
        // Given
        List<Country> expectedCountries = List.of(
                new Country("A", "Aa", 1., 1., new HashSet<>()),
                new Country("B", "Bb", 2., 2., new HashSet<>()),
                new Country("C", "Cc", 3., 3., new HashSet<>())
        );
        ObjectMapper mapper = new ObjectMapper();
        String expectedCountriesJson = mapper.writeValueAsString(expectedCountries);
        when(countryService.getCountries()).thenReturn(expectedCountries);

        // When
        mockMvc.perform(get("/api/v1/routing/countries"))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedCountriesJson));
        verify(countryService).getCountries();
    }

    @Test
    public void testGetCountry() throws Exception {
        // Given
        String code = "A";
        Country expectedCountry = new Country("A", "Aa", 1., 1., new HashSet<>());
        ObjectMapper mapper = new ObjectMapper();
        String expectedCountryJson = mapper.writeValueAsString(expectedCountry);
        when(countryService.getCountry(code)).thenReturn(expectedCountry);

        // When
        mockMvc.perform(get("/api/v1/routing/countries/{code}", code))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedCountryJson));
        verify(countryService).getCountry(code);
    }

    @Test
    public void testCreateCountry() throws Exception {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        ObjectMapper mapper = new ObjectMapper();
        String countryJson = mapper.writeValueAsString(country);
        when(countryService.createCountry(country)).thenReturn(country);

        // When
        mockMvc.perform(post("/api/v1/routing/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(countryJson)
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(countryJson));
        verify(countryService).createCountry(country);
    }

    @Test
    public void testUpdateCountry() throws Exception {
        // Given
        Country country = new Country("A", "Aa", 1., 1., new HashSet<>());
        ObjectMapper mapper = new ObjectMapper();
        String countryJson = mapper.writeValueAsString(country);
        when(countryService.updateCountry(country)).thenReturn(country);

        // When
        mockMvc.perform(put("/api/v1/routing/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(countryJson)
                )

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(countryJson));
        verify(countryService).updateCountry(country);
    }

    @Test
    public void testDeleteCountry() throws Exception {
        // Given
        String code = "A";
        doNothing().when(countryService).deleteCountry(code);

        // When
        mockMvc.perform(delete("/api/v1/routing/countries/{code}", code))

                // Then
                .andExpect(status().isNoContent());
        verify(countryService).deleteCountry(code);
    }
}
