package com.modsen.route.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modsen.route.service.services.RouteExecutingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RouteExecutingControllerTest {

    @Mock
    private RouteExecutingService routeExecutingService;

    @InjectMocks
    private RouteExecutingController routeExecutingController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(routeExecutingController).build();
    }

    @Test
    public void testGetRoute() throws Exception {
        String algorithm = "BFS";
        String from = "A";
        String to = "C";
        List<String> exceptedRoute = List.of("A", "B", "C");
        ObjectMapper mapper = new ObjectMapper();
        String exceptedRouteJson = mapper.writeValueAsString(exceptedRoute);
        when(routeExecutingService.getRoute(algorithm, from, to)).thenReturn(exceptedRoute);

        mockMvc.perform(get("/api/v1/routing/{algorithm}/{from}/{to}", algorithm, from, to))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(exceptedRouteJson));
        verify(routeExecutingService).getRoute(algorithm, from, to);
    }
}
