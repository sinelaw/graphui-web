package com.graphui.beta.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.Raphael.Circle;

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
		final Vector2D<Integer> pos = new Vector2D<Integer>(400,400); 
		final Vector2D<Integer> vel = new Vector2D<Integer>(20,20);
		final Circle c = raphael.new Circle(400,400,20);
		c.addDomHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Vector2D<Double> newPos = pos.add(vel);
				pos.setX(newPos.getX().intValue());
				pos.setY(newPos.getY().intValue());
				c.animate(new JSONObject(pos.asJSO()), 100);
			}}, ClickEvent.getType());
	}
}
