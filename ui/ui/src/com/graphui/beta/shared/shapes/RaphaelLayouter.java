package com.graphui.beta.shared.shapes;

import java.util.HashMap;
import java.util.Map.Entry;

import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.PathBuilder;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.Raphael.Path;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class RaphaelLayouter  {
	private Raphael canvas;

	public RaphaelLayouter(Raphael canvas) {
		this.canvas = canvas;
	}
	public void updateLayout(HashMap<Node<String>,LayoutShape<Shape,Double>> layout, int animTime, Vector2D<Double> offset) 
	{
		for (Entry<Node<String>, LayoutShape<Shape, Double>>  entry : layout.entrySet()) {
			updateShapeLayout(animTime, entry.getValue(), offset);
			Vector2D<Double> sourcePos = entry.getValue().getPosition().add(offset);
			
			for (Node<String> outNode : entry.getKey().getOutEdges()) {
				Vector2D<Double> targetPos = layout.get(outNode).getPosition().add(offset).subtract(sourcePos);
				PathBuilder builder = new PathBuilder();
				builder.M(sourcePos.getX(), sourcePos.getY());
				builder.l(targetPos.getX(), targetPos.getY());
				Path path = canvas.new Path(builder);
				path.toBack();
			}
		}
	}

	public void updateShapeLayout(int animTime,
			LayoutShape<Shape, Double> pshape, Vector2D<Double> offset) 
	{
		Shape shape = pshape.getShape();
		Vector2D<Double> pos = pshape.getPosition().add(offset);
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
