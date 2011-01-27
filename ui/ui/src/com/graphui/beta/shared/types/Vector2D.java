package com.graphui.beta.shared.types;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.graphui.beta.shared.types.interfaces.IPair;

public class Vector2D<T extends Number> implements IPair<T,T>
{
	T x, y;
	
	public Vector2D(Vector2D<T> other) {
		this.x = other.getX();
		this.y = other.getY();
	}
	public Vector2D(T x, T y) {
		this.x = x;
		this.y = y;
	}
	public Vector2D<T> copy() {
		return new Vector2D<T>(this);
	}
	
	@Override
	public   T getFirst() {
		return this.x;
	}
	@Override
	public   void setFirst(T val) {
		this.x = val;
	}
	@Override
	public   T getSecond() {
		return this.y;
	}
	@Override
	public   void setSecond(T val) {
		this.y = val;
	}
	
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
	
	public String toString() {
		return "x: " + this.x + ", y: " + this.y;
	}
	
	public <U extends Number> Vector2D<Double> add(Vector2D<U> other)
	{
		return new Vector2D<Double>(
				this.getX().doubleValue() + other.getX().doubleValue(), 
				this.getY().doubleValue() + other.getY().doubleValue());
	}

	public <U extends Number> Vector2D<Double> subtract(Vector2D<U> other)
	{
		return this.add(other.scale(-1.0));
	}
	
	public Vector2D<Double> scale(Double scalar)
	{
		return new Vector2D<Double>(
				this.getX().doubleValue() * scalar, 
				this.getY().doubleValue() * scalar);
	}

	
	public JSONObject asJSONObject(String xKey, String yKey) {
		JSONObject jsPos = new JSONObject();
		jsPos.put(xKey, new JSONNumber(this.getX().doubleValue()));
		jsPos.put(yKey, new JSONNumber(this.getY().doubleValue()));
		return jsPos;
	}
}