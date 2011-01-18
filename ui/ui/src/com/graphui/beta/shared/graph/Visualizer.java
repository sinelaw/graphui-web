package com.graphui.beta.shared.graph;

import java.util.ArrayList;

import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.LayoutShape;

public interface Visualizer {
	<T, U, S extends Number> 
		ArrayList<LayoutShape<U,S>> Visualize(ArrayList<Node<T>> graph);
}
