package com.modsen.route.service.utils;

import com.modsen.graph.api.Graph;
import com.modsen.graph.api.methods.BreadthFirstSearch;
import com.modsen.graph.api.methods.Dijkstra;

public class GraphMethodsFactory {

    public static <V> Graph<V> getGraph() {
        return new Graph<>();
    }

    public static <V> Dijkstra<V> getDijkstra(Graph<V> graph) {
        return new Dijkstra<>(graph);
    }

    public static <V> BreadthFirstSearch<V> getBreadthFirstSearch(Graph<V> graph) {
        return new BreadthFirstSearch<>(graph);
    }
}
