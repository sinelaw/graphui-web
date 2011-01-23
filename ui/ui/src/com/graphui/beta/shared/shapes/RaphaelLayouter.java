package com.graphui.beta.shared.shapes;

import java.util.ArrayList;

import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class RaphaelLayouter  {
	public static void updateLayout(ArrayList<LayoutShape<Shape, Double>> elems, int animTime) 
	{
		for (LayoutShape<Shape, Double> pshape : elems) {
			Shape shape = pshape.getShape();
			Vector2D<Double> pos = pshape.getPosition();
			Vector2D<Double> scale = pshape.getScale();
			shape.animate(pos.add(new Vector2D<Double>(100.0,100.0)).asJSONObject("cx","cy"), animTime);
			shape.animate(scale.asJSONObject("rx","ry"), animTime);
		}
	}
}
