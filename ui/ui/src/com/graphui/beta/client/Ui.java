package com.graphui.beta.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.graphui.beta.shared.graph.visualizers.GridVisualizer;
import com.graphui.beta.shared.shapes.RaphaelLayouter;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ui implements EntryPoint {
	public static Raphael raphael;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		raphael = new Raphael(800,600);
		RootPanel.get("root").add(raphael);
		
		Node<String> a = Node.create("a", null, null);
		Node<String> b = Node.create("b", null, null);
		Node<String> c = Node.create("c", null, null);
		Node<String> d = Node.create("d", null, Arrays.asList(a,b,c));
		Node<String> e = Node.create("e", Arrays.asList(a,b,c), null);
		
		List<Node<String>> graph = Arrays.asList(
				a,b,c,d,e
		);
		
		GridVisualizer<String> viz = new GridVisualizer<String>();
		
		
		ArrayList<LayoutShape<Shape, Double>> layout = viz.Visualize(graph);
		RaphaelLayouter.updateLayout(layout, 500);
	}
}
