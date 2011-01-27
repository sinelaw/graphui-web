package com.graphui.beta.client;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RootPanel;
import com.graphui.beta.shared.graph.visualizers.TreeVisualizer;
import com.graphui.beta.shared.shapes.RaphaelLayouter;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.ListUtils;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ui implements EntryPoint {
	private static final int CANVAS_HEIGHT = 400;
	private static final int CANVAS_WIDTH = 800;

	public static Raphael raphael;
	
	/**
	 * This is the entry point method.
	 */
	protected LayoutShape<Shape,Double> grabbed = null;
	protected HashMap<Node<String>,LayoutShape<Shape,Double>> layout = new HashMap<Node<String>, LayoutShape<Shape,Double>>();
	private Vector2D<Double> offset = new Vector2D<Double>(CANVAS_WIDTH/2.0,60.0);
	
	public void onModuleLoad() {
		raphael = new Raphael(CANVAS_WIDTH,CANVAS_HEIGHT);
		raphael.addStyleName("canvas");
		RootPanel.get("root").add(raphael);
		final RaphaelLayouter layouter = new RaphaelLayouter(raphael);
		final Vector2D<Double> zeroVec = new Vector2D<Double>(0.0,0.0);
		
		raphael.addDomHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (null == grabbed) {
					return;
				}
				grabbed.setPosition(new Vector2D<Double>((double)event.getX(), (double)event.getY()));
				layouter.updateShapeLayout(0, grabbed, zeroVec);
			}
		}, MouseMoveEvent.getType());
		
		Node<String> a = Node.create("a");
		Node<String> b = Node.create("b");
		Node<String> c = Node.create("c");
		Node<String> d = Node.create("d");
		Node<String> e = Node.create("e");
		
		a.connectTo(b);
		a.connectTo(c);
		b.connectTo(e);
		b.connectTo(d);
		c.connectTo(d);
		d.connectTo(e);
		
		List<Node<String>> graph = ListUtils.create(
				a,b,c,d,e
		);
		String[] colors = new String[] {
			"r#d20000-#d20000:70-#ffa7a7",
			"r#00d200-#00d200:70-#a7ffa7",
			"r#0000d2-#0000d2:70-#a7a7ff",
			"r#00d2d2-#00d2d2:70-#a7ffff",
			"r#d2d200-#d2d200:70-#ffffa7",
		};
		for (Node<String> node : graph) {
			Shape shape = raphael.new Ellipse(0, 0, 1.0, 1.0);
			shape.attr("fill",colors[Random.nextInt(colors.length)]);
			shape.attr("stroke-opacity",0.2);
			shape.attr("stroke-width",0.1);
			Vector2D<Double> pos = new Vector2D<Double>(0.0,0.0);
			Vector2D<Double> scale = new Vector2D<Double>(1.0,1.0);
			layout.put(node, new LayoutShape<Shape,Double>(shape, pos, scale));
		}
		
		TreeVisualizer<String> viz = new TreeVisualizer<String>();
		
		
		viz.UpdateLayout(layout);
		
		for (final LayoutShape<Shape,Double> lshape : layout.values()) {
			final Shape shape = lshape.getShape();
			//lshape.getPosition().setX(Random.nextDouble()*CANVAS_WIDTH);
			//lshape.getPosition().setY(Random.nextDouble()*CANVAS_HEIGHT);
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
						lshape.getShape().toFront();
						lshape.getShape().attr("stroke-width", 1.0);
						lshape.getShape().attr("opacity", 0.3);
						grabbed = lshape;
					}
				}
			}, ClickEvent.getType());
		}
		
		layouter.updateLayout(layout, 500, offset);
	}
}
