package com.graphui.beta.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
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
	Vector2D<Double> pos = new Vector2D<Double>(400.0,400.0); 
	Vector2D<Double> vel = new Vector2D<Double>(20.0,20.0);

	public void onModuleLoad() {
		raphael = new Raphael(800,600);
		RootPanel.get("root").add(raphael);
		final Circle c = raphael.new Circle(400,400,20);
		c.attr("fill","#ff0000");
		c.attr("stroke","#ff0000");
		c.attr("stroke-opacity",0.2);
		c.attr("stroke-width",5.0);
		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			@Override
			public boolean execute() {
				pos = pos.add(vel);
				if (pos.getX() > 700 || pos.getX() < 100) {
					vel.setX(-vel.getX());
				}
				if (pos.getY() > 500 || pos.getY() < 100) {
					vel.setY(-vel.getY());
				}
				c.animate(pos.asJSONObject("cx", "cy"), 200);
				return true;
			}
		}, 200);
		
//		c.addDomHandler(new ClickHandler(){
//			@Override
//			public void onClick(ClickEvent event) {
//			}}, ClickEvent.getType());
	}
}
