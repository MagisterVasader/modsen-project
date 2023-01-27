package com.modsen.graph.api.methods;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Dijkstra<V> extends SearchPath<V> {

    private static final Integer INFINITY_DIStANCE = Integer.MAX_VALUE;

    public Dijkstra(Graph<V> graph) {
        super(graph);
    }

    @Override
    public List<V> findPath(V source, V target) {
        if (graph.containsVertex(source) && graph.containsVertex(target)) {
            if (source.equals(target)) {
                return List.of(source);
            }

            Map<V, Integer> distance = new HashMap<>();
            Map<V, V> previous = new HashMap<>();
            Set<V> unvisited = new HashSet<>();
            Queue<V> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

            for (V vertex : graph.getVertexSet()) {
                distance.put(vertex, INFINITY_DIStANCE);
                previous.put(vertex, null);
                unvisited.add(vertex);
            }
            distance.put(source, 0);
            queue.add(source);

            while (!queue.isEmpty()) {
                V current = queue.poll();
                unvisited.remove(current);

                for (Edge<V> edge : graph.getEdgesSet()) {
                    if (edge.getSource().equals(current)) {
                        V neighbor = edge.getTarget();
                        if (unvisited.contains(neighbor)) {
                            int newDistance = distance.get(current) + edge.getWeight();
                            if (newDistance < distance.get(neighbor)) {
                                distance.put(neighbor, newDistance);
                                previous.put(neighbor, current);
                                queue.add(neighbor);
                            }
                        }
                    }
                }
            }

            if (INFINITY_DIStANCE.equals(distance.get(target))) {
                return Collections.emptyList();
            }
            return getPath(previous, target);
        }
        return Collections.emptyList();
    }
}
