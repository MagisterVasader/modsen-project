package com.modsen.graph.api.methods;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    private Dijkstra<String> dijkstra;
    private Graph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        dijkstra = new Dijkstra<>(graph);
    }

    @Test
    public void testFindPathForConnectedVertices() {
        List<String> expectedPath = List.of("A", "B", "C", "D");
        List<String> actualPath = dijkstra.findPath("A", "D");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForDisconnectedVertices() {
        graph.removeEdge(new Edge<>("B", "C", 1));
        List<String> expectedPath = new ArrayList<>();
        List<String> actualPath = dijkstra.findPath("A", "D");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForSameSourceAndTargetVertices() {
        List<String> expectedPath = List.of("A");
        List<String> actualPath = dijkstra.findPath("A", "A");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForNonExistentVertex() {
        graph.removeVertex("A");
        List<String> expectedPath = new ArrayList<>();
        List<String> actualPath = dijkstra.findPath("A", "D");
        assertEquals(expectedPath, actualPath);
    }
}
