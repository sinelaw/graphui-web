package com.graphui.beta.shared.shapes;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
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
			shape.animate(pos.asJSONObject("x","y"), animTime);
			//shape.animate(scale.asJSONObject("xtimes","ytimes"), animTime);
		}
	}
}
