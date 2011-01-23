package com.graphui.beta.shared.graph;

import java.util.HashMap;

import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;

public interface Visualizer<T, U, S extends Number> {
	void UpdateLayout(HashMap<Node<T>,LayoutShape<U,S>> graph);
}
