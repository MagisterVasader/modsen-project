package com.modsen.graph.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edge<V> {

    private V source;
    private V target;
    private int weight;
}
