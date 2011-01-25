package com.graphui.beta.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.graphui.beta.shared.graph.visualizers.GridVisualizer;
import com.graphui.beta.shared.shapes.RaphaelLayouter;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Vector2D;
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
	protected LayoutShape<Shape,Double> grabbed = null;
	protected HashMap<Node<String>,LayoutShape<Shape,Double>> layout = new HashMap<Node<String>, LayoutShape<Shape,Double>>();
	
	public void onModuleLoad() {
		raphael = new Raphael(800,600);
		raphael.addStyleName("canvas");
		RootPanel.get("root").add(raphael);
		
		raphael.addDomHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (null == grabbed) {
					return;
				}
				grabbed.setPosition(new Vector2D<Double>((double)event.getX(), (double)event.getY()));
				RaphaelLayouter.updateLayout(layout.values(), 0);
			}
		}, MouseMoveEvent.getType());
		
		Node<String> a = Node.create("a", null, null);
		Node<String> b = Node.create("b", null, null);
		Node<String> c = Node.create("c", null, null);
		Node<String> d = Node.create("d", null, Arrays.asList(a,b,c));
		Node<String> e = Node.create("e", Arrays.asList(a,b,c), null);
		
		List<Node<String>> graph = Arrays.asList(
				a,b,c,d,e
		);
		
		for (Node<String> node : graph) {
			Shape shape = raphael.new Ellipse(0, 0, 1.0, 1.0);
			shape.attr("fill","r#d20000-#ffa7a7");
			shape.attr("stroke-opacity",0.2);
			shape.attr("stroke-width",0.1);
			Vector2D<Double> pos = new Vector2D<Double>(0.0,0.0);
			Vector2D<Double> scale = new Vector2D<Double>(1.0,1.0);
			layout.put(node, new LayoutShape<Shape,Double>(shape, pos, scale));
		}
		
		GridVisualizer<String> viz = new GridVisualizer<String>();
		
		
		viz.UpdateLayout(layout);
		
		for (final LayoutShape<Shape,Double> lshape : layout.values()) {
			final Shape shape = lshape.getShape();
			shape.addDomHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					if (grabbed != null) { 
						grabbed.getShape().attr("stroke-width", 0.1);
						grabbed.getShape().attr("opacity", 1.0);
					}
					if (lshape == grabbed) {
						grabbed = null;
					}
					else {
						lshape.getShape().attr("stroke-width", 1.0);
						lshape.getShape().attr("opacity", 0.7);
						grabbed = lshape;
					}
				}
			}, ClickEvent.getType());
		}
		
		RaphaelLayouter.updateLayout(layout.values(), 500);
	}
}
