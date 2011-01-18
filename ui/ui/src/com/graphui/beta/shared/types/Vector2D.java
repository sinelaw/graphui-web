package com.graphui.beta.shared.types;

import com.google.gwt.core.client.JavaScriptObject;
import com.graphui.beta.shared.types.interfaces.IPair;

public class Vector2D<T extends Number> extends JavaScriptObject implements IPair<T,T>
{
	protected Vector2D() {	}
	
	public static native <U extends Number> Vector2D<U> create(U x, U y)
	/*-{
		return {X: first, Y: second}; 
	}-*/;

	public final native T getFirst() /*-{
		return this.X;
	}-*/;
	public final native void setFirst(T val) /*-{
		this.X = val;
	}-*/;
	
	public final native T getSecond() /*-{
		return this.Y;
	}-*/;
	public final native void setSecond(T val) /*-{
		this.Y = val;
	}-*/;
	
	public T getX() {
		return this.getFirst();
	}
	public T getY() {
		return this.getSecond();
	}
	public void setX(T val) {
		this.setFirst(val);
	}
	public void setY(T val) {
		this.setSecond(val);
	}
	
	public <U extends Number> Vector2D<Double> add(Vector2D<U> other)
	{
		return Vector2D.<Double>create(
				this.getX().doubleValue() + other.getX().doubleValue(), 
				this.getY().doubleValue() + other.getY().doubleValue());
	}

	public <U extends Number> Vector2D<Double> scale(Double scalar)
	{
		return Vector2D.<Double>create(
				this.getX().doubleValue() * scalar, 
				this.getY().doubleValue() * scalar);
	}

}