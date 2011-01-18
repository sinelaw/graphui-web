package com.graphui.beta.shared.types;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;


public class Node<T> extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected Node() {
	}
	
	public static native <U> Node<U> create(U value, ArrayList<Node<U>> outEdges, ArrayList<Node<U>> inEdges)
	/*-{
		return {Value: value, OutEdges: outEdges, InEdges: inEdges}; 
	}-*/;

	public final native String getValue() /*-{
		return this.Value;
	}-*/;

	public final native ArrayList<Node<T>> getOutEdges() /*-{
		return this.OutEdges;
	}-*/;

	public final native ArrayList<Node<T>> getInEdges() /*-{
		return this.OutInEdges;
	}-*/;
}