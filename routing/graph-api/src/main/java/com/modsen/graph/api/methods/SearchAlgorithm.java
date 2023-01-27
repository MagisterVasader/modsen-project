package com.modsen.graph.api.methods;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SearchAlgorithm {

    DIJKSTRA,
    BFS;

    public static boolean checkName(String algorithm) {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.toSet())
                .contains(algorithm);
    }
}
