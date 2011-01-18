package com.graphui.beta.shared.types;

import com.google.gwt.core.client.JavaScriptObject;
import com.graphui.beta.shared.types.interfaces.IPair;

public class Pair<T,S>  extends JavaScriptObject implements IPair<T,S>
{
	protected Pair() {	}
	
	public static native <U,V> Pair<U,V> create(U first, V second)
	/*-{
		return {First: first, Second: second}; 
	}-*/;

	public final native T getFirst() /*-{
		return this.First;
	}-*/;
	public final native void setFirst(T val) /*-{
		this.First = val;
	}-*/;
	
	public final native S getSecond() /*-{
		return this.Second;
	}-*/;
	public final native void setSecond(S val) /*-{
		this.Second = val;
	}-*/;
}