package com.modsen.graph.api.methods;

import com.modsen.graph.api.Edge;
import com.modsen.graph.api.Graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<V> extends SearchPath<V> {

    public BreadthFirstSearch(Graph<V> graph) {
        super(graph);
    }

    @Override
    public List<V> findPath(V source, V target) {
        if (graph.containsVertex(source) && graph.containsVertex(target)) {
            if (source.equals(target)) {
                return List.of(source);
            }

            Queue<V> queue = new LinkedList<>();
            Map<V, V> previous = new HashMap<>();
            Set<V> visited = new HashSet<>();

            queue.add(source);
            visited.add(source);

            while (!queue.isEmpty()) {
                V current = queue.poll();

                for (Edge<V> edge : graph.getEdgesSet()) {
                    V neighbor;
                    if (edge.getSource().equals(current)) {
                        neighbor = edge.getTarget();
                    } else if (edge.getTarget().equals(current)) {
                        neighbor = edge.getSource();
                    } else {
                        continue;
                    }

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        previous.put(neighbor, current);
                        queue.add(neighbor);

                        if (neighbor.equals(target)) {
                            return getPath(previous, target);
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
