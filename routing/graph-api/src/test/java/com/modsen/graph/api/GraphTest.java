package com.modsen.graph.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
    private Graph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.addVertex("B"));
        assertFalse(graph.addVertex(null));
        assertFalse(graph.addVertex("A"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");

        assertTrue(graph.removeVertex("A"));
        assertTrue(graph.getEdgesSet().isEmpty());
        assertFalse(graph.removeVertex(null));
        assertFalse(graph.removeVertex("C"));
    }

    @Test
    public void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B"));
        assertFalse(graph.getEdgesSet().isEmpty());
        assertFalse(graph.addEdge(null, "D"));
        assertFalse(graph.addEdge("C", null));
        assertFalse(graph.addEdge(null, null));
        assertFalse(graph.addEdge("C", "D"));
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        Edge<String> edge = new Edge<>("A", "B", 1);
        graph.addEdge("A", "B");

        assertTrue(graph.removeEdge(edge));
        assertFalse(graph.removeEdge(null));
        assertFalse(graph.removeEdge(new Edge<>("C", "D", 1)));
    }

    @Test
    public void testContainsVertex() {
        graph.addVertex("A");

        assertTrue(graph.containsVertex("A"));
        assertFalse(graph.containsVertex(null));
        assertFalse(graph.containsVertex("C"));
    }

    @Test
    public void testContainsEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        Edge<String> edge = new Edge<>("A", "B", 1);
        graph.addEdge("A", "B");

        assertTrue(graph.containsEdge(edge));
        assertFalse(graph.containsEdge(null));
        assertFalse(graph.containsEdge(new Edge<>("C", "D", 1)));
    }
}
