package com.modsen.graph.api.methods;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra<V> extends SearchPath<V> {

    public Dijkstra(Graph<V> graph) {
        super(graph);
    }

    @Override
    public List<V> findPath(V source, V target) {
        if (isExistInGraph(source) && isExistInGraph(target)) {
            if (source.equals(target)) {
                return List.of(source);
            }

            Map<V, Integer> distance = new HashMap<>();
            Map<V, V> previous = new HashMap<>();
            Set<V> unvisited = new HashSet<>();
            PriorityQueue<V> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

            for (V vertex : graph.getVertexSet()) {
                distance.put(vertex, Integer.MAX_VALUE);
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

            if (distance.get(target) == Integer.MAX_VALUE) {
                return new ArrayList<>();
            }
            return getPath(previous, target);
        }
        return new ArrayList<>();
    }
}
