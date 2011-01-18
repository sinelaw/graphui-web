package com.graphui.beta.shared.graph;

import java.util.ArrayList;

import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Pair;

public interface Visualizer {
	<T> ArrayList<Pair<Double,Double>> Visualize(ArrayList<Node<T>> graph);
}
