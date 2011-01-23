package com.graphui.beta.shared.graph.visualizers;

import java.util.ArrayList;
import java.util.List;

import com.graphui.beta.client.Ui;
import com.graphui.beta.shared.graph.Visualizer;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class GridVisualizer<T> implements Visualizer<T,Shape,Double> {
	protected static double speed = 20.0;
	protected static double size = 10.0;
	
	@Override
	public ArrayList<LayoutShape<Shape, Double>> Visualize(List<Node<T>> graph) 
	{
		ArrayList<LayoutShape<Shape,Double>> layout = new ArrayList<LayoutShape<Shape,Double>>();
		Vector2D<Double> positionAcc = new Vector2D<Double>(0.0, 0.0);
		Vector2D<Double> xVel = new Vector2D<Double>(1.0, 0.0).scale(speed);
		Vector2D<Double> yVel = new Vector2D<Double>(0.0, 1.0).scale(speed);
		Vector2D<Double> scale = new Vector2D<Double>(1.0, 1.0).scale(size);
		
		for (Node<T> node : graph) {
			positionAcc = positionAcc.add(xVel);
			if (positionAcc.getX() > 800) {
				positionAcc.add(yVel);
				positionAcc.setX(0.0);
			}
			Shape shape = Ui.raphael.new Ellipse(0,0,1.0,1.0);
			layout.add(new LayoutShape<Shape,Double>(shape, copyVec(positionAcc), copyVec(scale)));
		}
		return layout;
	}

	private Vector2D<Double> copyVec(Vector2D<Double> vec) {
		return new Vector2D<Double>(vec);
	}

}
