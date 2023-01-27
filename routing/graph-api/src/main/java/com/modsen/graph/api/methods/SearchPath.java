package com.modsen.graph.api.methods;

import com.modsen.graph.api.Graph;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class SearchPath<V> {

    protected final Graph<V> graph;

    public abstract List<V> findPath(V source, V target);

    protected List<V> getPath(Map<V, V> previous, V target) {
        List<V> path = new ArrayList<>();
        V current = target;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        return Collections.unmodifiableList(path);
    }
}
