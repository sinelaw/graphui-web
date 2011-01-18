package com.graphui.beta.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.hydro4ge.raphaelgwt.client.Raphael;

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
	}
}
