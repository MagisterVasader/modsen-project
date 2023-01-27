package com.modsen.graph.api.methods;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    private BreadthFirstSearch<Integer> breadthFirstSearch;
    private Graph<Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        breadthFirstSearch = new BreadthFirstSearch<>(graph);
    }

    @Test
    public void testFindPathForConnectedVertices() {
        List<Integer> expectedPath = List.of(1, 2, 3, 4);
        List<Integer> actualPath = breadthFirstSearch.findPath(1, 4);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForDisconnectedVertices() {
        graph.removeEdge(new Edge<>(2, 3, 1));
        List<Integer> expectedPath = new ArrayList<>();
        List<Integer> actualPath = breadthFirstSearch.findPath(1, 4);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForSameSourceAndTargetVertices() {
        List<Integer> expectedPath = List.of(1);
        List<Integer> actualPath = breadthFirstSearch.findPath(1, 1);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForNonExistentVertex() {
        graph.removeVertex(1);
        List<Integer> expectedPath = new ArrayList<>();
        List<Integer> actualPath = breadthFirstSearch.findPath(1, 4);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testFindPathForNonNullVertex() {
        List<Integer> expectedPath = new ArrayList<>();
        List<Integer> actualPath = breadthFirstSearch.findPath(null, null);
        assertEquals(expectedPath, actualPath);
    }
}
