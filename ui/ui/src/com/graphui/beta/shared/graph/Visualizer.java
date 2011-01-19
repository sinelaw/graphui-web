package com.graphui.beta.shared.graph;

import java.util.ArrayList;
import java.util.List;

import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;

public interface Visualizer<T, U, S extends Number> {
	ArrayList<LayoutShape<U,S>> Visualize(List<Node<T>> graph);
}
