package com.graphui.beta.shared.graph.visualizers;

import java.util.HashMap;
import java.util.Map;

import com.graphui.beta.shared.graph.Visualizer;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class GridVisualizer<T> implements Visualizer<T,Shape,Double> {
	protected static double speed = 20.0;
	protected static double size = 10.0;
	
	@Override
	public void UpdateLayout(HashMap<Node<T>, LayoutShape<Shape, Double>> graph) 
	{
		Vector2D<Double> positionAcc = new Vector2D<Double>(0.0, 0.0);
		Vector2D<Double> xVel = new Vector2D<Double>(1.0, 0.0).scale(speed);
		Vector2D<Double> yVel = new Vector2D<Double>(0.0, 1.0).scale(speed);
		Vector2D<Double> scale = new Vector2D<Double>(1.0, 1.0).scale(size);
		
		for (Map.Entry<Node<T>,LayoutShape<Shape,Double>> entry : graph.entrySet()) {
			positionAcc = positionAcc.add(xVel);
			if (positionAcc.getX() > 800) {
				positionAcc.add(yVel);
				positionAcc.setX(0.0);
			}
			entry.getValue().setPosition(copyVec(positionAcc));
			entry.getValue().setScale(copyVec(scale));
		}
	}

	private Vector2D<Double> copyVec(Vector2D<Double> vec) {
		return new Vector2D<Double>(vec);
	}


}
