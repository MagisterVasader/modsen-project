package com.modsen.route.service.controllers;

import com.modsen.route.service.services.RouteExecutingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routing")
public class RouteExecutingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteExecutingController.class);

    private final RouteExecutingService routeExecutingService;

    @GetMapping("/{algorithm}/{from}/{to}")
    public ResponseEntity<List<String>> getRoute(@PathVariable String algorithm,
                                                 @PathVariable String from,
                                                 @PathVariable String to) {
        LOGGER.info("[RT] Getting route between " + from + " to " + to + " using " + algorithm);
        List<String> route = routeExecutingService.getRoute(algorithm, from, to);
        LOGGER.info("[RT] Found route: " + route);
        return ResponseEntity.ok(route);
    }
}
