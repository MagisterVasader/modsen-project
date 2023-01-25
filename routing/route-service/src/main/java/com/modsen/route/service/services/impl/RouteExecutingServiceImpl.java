package com.modsen.route.service.services.impl;


import com.modsen.graph.api.methods.SearchAlgorithm;
import com.modsen.route.service.model.Border;
import com.modsen.route.service.model.Country;
import com.modsen.route.service.services.CountryService;
import com.modsen.route.service.services.RouteExecutingService;
import com.modsen.route.service.utils.GraphMethodsFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteExecutingServiceImpl implements RouteExecutingService {

    private final CountryService countryService;

    @Override
    public List<String> getRoute(String algorithm, String from, String to) {
        var graph = GraphMethodsFactory.<String>getGraph();
        var countries = countryService.getCountries();

        var vertexes = countries.stream()
                .map(Country::getCode)
                .collect(Collectors.toSet());
        graph.setVertexSet(vertexes);

        for (Country country : countries) {
            country.getBorders().stream()
                    .map(Border::getId)
                    .forEach(id -> graph.addEdge(id.getCountryCode(), id.getBorderCountryCode()));
        }

        switch (SearchAlgorithm.valueOf(algorithm)) {
            case DIJKSTRA:
                var dijkstra = GraphMethodsFactory.getDijkstra(graph);
                return dijkstra.findPath(from, to);
            case BFS:
                var breadthFirstSearch = GraphMethodsFactory.getBreadthFirstSearch(graph);
                return breadthFirstSearch.findPath(from, to);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }
}
