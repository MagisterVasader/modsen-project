package com.modsen.route.service.services.impl;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;
import com.modsen.graph.api.methods.BreadthFirstSearch;
import com.modsen.graph.api.methods.Dijkstra;
import com.modsen.route.service.model.Border;
import com.modsen.route.service.model.BorderId;
import com.modsen.route.service.model.Country;
import com.modsen.route.service.services.CountryService;
import com.modsen.route.service.utils.GraphMethodsFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteExecutingServiceImplTest {

    private MockedStatic<GraphMethodsFactory> factory;
    private List<Country> countries;
    private Graph<String> graph;
    private Graph<String> resultGraph;

    @Mock
    private Dijkstra<String> dijkstra;

    @Mock
    private BreadthFirstSearch<String> breadthFirstSearch;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private RouteExecutingServiceImpl routeExecutingService;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();

        Country a = new Country("A", "Aa", 1., 1., new HashSet<>());
        Country b = new Country("B", "Bb", 2., 2., new HashSet<>());
        Country c = new Country("C", "Cc", 3., 3., new HashSet<>());
        BorderId borderABId = new BorderId("A", "B");
        BorderId borderBCId = new BorderId("B", "C");
        Border borderAB = new Border(borderABId, a, b);
        Border borderBC = new Border(borderBCId, b, c);

        a.setBorders(Set.of(borderAB));
        b.setBorders(Set.of(borderBC));

        countries = List.of(a, b, c);

        Edge<String> edgeAB = new Edge<>("A", "B", 1);
        Edge<String> edgeBC = new Edge<>("B", "C", 1);
        Set<String> vertexes = Set.of("A", "B", "C");
        Set<Edge<String>> edges = Set.of(edgeAB, edgeBC);

        resultGraph = new Graph<>();
        resultGraph.setVertexSet(vertexes);
        resultGraph.setEdgesSet(edges);

        factory = mockStatic(GraphMethodsFactory.class);
        factory.when(GraphMethodsFactory::<String>getGraph).thenReturn(graph);
        factory.when(() -> GraphMethodsFactory.getDijkstra(Mockito.any())).thenReturn(dijkstra);
        factory.when(() -> GraphMethodsFactory.getBreadthFirstSearch(Mockito.any())).thenReturn(breadthFirstSearch);
    }

    @AfterEach
    public void tearDown() {
        factory.close();
    }

    @Test
    public void testGetRouteWithDijkstraAlgorithm() {
        // Given
        when(dijkstra.findPath("A", "C")).thenReturn(List.of("A", "B", "C"));
        when(countryService.getCountries()).thenReturn(countries);

        // When
        List<String> route = routeExecutingService.getRoute("DIJKSTRA", "A", "C");

        // Then
        assertEquals(List.of("A", "B", "C"), route);
        assertEquals(resultGraph.getVertexSet(), graph.getVertexSet());
        assertEquals(resultGraph.getEdgesSet(), graph.getEdgesSet());
    }

    @Test
    public void testGetRouteWithBfsAlgorithm() {
        // Given
        when(breadthFirstSearch.findPath("A", "C")).thenReturn(List.of("A", "B", "C"));
        when(countryService.getCountries()).thenReturn(countries);

        // When
        List<String> route = routeExecutingService.getRoute("BFS", "A", "C");

        // Then
        assertEquals(List.of("A", "B", "C"), route);
        assertEquals(resultGraph.getVertexSet(), graph.getVertexSet());
        assertEquals(resultGraph.getEdgesSet(), graph.getEdgesSet());
    }

    @Test
    public void testGetRouteWithUnsupportedAlgorithm() {
        // Given
        when(countryService.getCountries()).thenReturn(countries);

        assertThrows(IllegalArgumentException.class, () -> routeExecutingService.getRoute("UNSUPPORTED", "A", "C"));
    }
}
