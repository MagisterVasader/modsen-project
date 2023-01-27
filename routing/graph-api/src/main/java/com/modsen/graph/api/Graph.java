package com.modsen.graph.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Graph<V> {

    private static final int DEFAULT_WEIGHT = 1;
    private Set<V> vertexSet;
    private Set<Edge<V>> edgesSet;

    public Graph() {
        this.vertexSet = new HashSet<>();
        this.edgesSet = new HashSet<>();
    }

    public boolean addVertex(V ver) {
        if (ver == null) {
            return false;
        }
        return vertexSet.add(ver);
    }

    public boolean removeVertex(V ver) {
        if (ver == null) {
            return false;
        }
        if (vertexSet.contains(ver)) {
            edgesSet.removeIf(e -> e.getTarget() == ver || e.getSource() == ver);
            vertexSet.remove(ver);
            return true;
        }
        return false;
    }

    public boolean addEdge(V ver1, V ver2) {
        if (ver1 == null && ver2 == null) {
            return false;
        }
        if (vertexSet.contains(ver1) && vertexSet.contains(ver2)) {
            var edge = new Edge<>(ver1, ver2, DEFAULT_WEIGHT);
            edgesSet.add(edge);
            return true;
        }
        return false;
    }

    public boolean removeEdge(Edge<V> edge) {
        if (edge == null) {
            return false;
        }
        return edgesSet.remove(edge);
    }

    public boolean containsVertex(V ver) {
        return ver != null && vertexSet.contains(ver);
    }

    public boolean containsEdge(Edge<V> edge) {
        return edge != null && edgesSet.contains(edge);
    }
}
