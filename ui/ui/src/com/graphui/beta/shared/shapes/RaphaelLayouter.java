package com.graphui.beta.shared.shapes;

import java.util.Collection;

import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class RaphaelLayouter  {
	public static void updateLayout(Collection<LayoutShape<Shape, Double>> elems, int animTime) 
	{
		for (LayoutShape<Shape, Double> pshape : elems) {
			Shape shape = pshape.getShape();
			Vector2D<Double> pos = pshape.getPosition();
			Vector2D<Double> scale = pshape.getScale();
			if (animTime > 0) {
				shape.animate(pos.asJSONObject("cx","cy"), animTime);
				shape.animate(scale.asJSONObject("rx","ry"), animTime);
			}
			else {
				shape.attr("cx", pos.getX());
				shape.attr("cy", pos.getY());
				shape.attr("rx", scale.getX());
				shape.attr("ry", scale.getY());
			}
		}
	}
}
