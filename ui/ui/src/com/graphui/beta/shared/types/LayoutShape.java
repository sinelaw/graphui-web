package com.graphui.beta.shared.types;



public class LayoutShape<T, U extends Number> {
	protected Vector2D<U> position;
	protected Vector2D<U> scale;
	protected T shape;
	
	public LayoutShape(T shape, Vector2D<U> position, Vector2D<U> scale) {
		this.setPosition(position);
		this.setShape(shape);
		this.setScale(scale);
	}
	
	public Vector2D<U> getPosition() {
		return this.position;
	}
	public void setPosition(Vector2D<U> position) {
		this.position = position;
	}
	public Vector2D<U> getScale() {
		return this.scale;
	}
	public void setScale(Vector2D<U> scale) {
		this.scale = scale;
	}
	public T getShape() {
		return this.shape;
	}
	public void setShape(T shape) {
		this.shape = shape;
	}
	
	
}
