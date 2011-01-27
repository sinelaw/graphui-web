package com.graphui.beta.shared.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;


public class Node<T> extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected Node() {
	}
	
	public static class CopyGraphResult<U> {
		protected final HashMap<Node<U>, Node<U>> origToCopy = new HashMap<Node<U>, Node<U>>();
		protected final HashMap<Node<U>, Node<U>> copyToOrig = new HashMap<Node<U>, Node<U>>();
		public HashMap<Node<U>, Node<U>> getOrigToCopy() {
			return origToCopy;
		}
		public HashMap<Node<U>, Node<U>> getCopyToOrig() {
			return copyToOrig;
		}
	}

	public static <U> CopyGraphResult<U> copyGraph(Collection<Node<U>> graph)
	{
		CopyGraphResult<U> res = new CopyGraphResult<U>();
		for (Node<U> orig : graph) {
			res.getCopyToOrig().put(Node.<U>create(orig, res.getOrigToCopy()), orig);
		}
		return res;
	}
	
	public static <U> Node<U> create(Node<U> origNode, HashMap<Node<U>,Node<U>> copyingNodes) 
	{
		 
		Node<U> copiedAlready = copyingNodes.get(origNode);
		if (null != copiedAlready) {
			return copiedAlready;
		}
		ArrayList<Node<U>> outEdges = new ArrayList<Node<U>>();
		ArrayList<Node<U>> inEdges = new ArrayList<Node<U>>();
		
		Node<U> thisNode = Node.<U>create(origNode.getValue(), null, null);
		copyingNodes.put(origNode, thisNode);
		
		for (Node<U> outNode : origNode.getOutEdges()) {
			outEdges.add(Node.<U>create(outNode, copyingNodes));
		}
		for (Node<U> inNode : origNode.getInEdges()) {
			inEdges.add(Node.<U>create(inNode, copyingNodes));
		}
		return thisNode;
	}

	public static native <U> Node<U> create(U value, List<Node<U>> outEdges, List<Node<U>> inEdges)
	/*-{
		return {Value: value, OutEdges: outEdges, InEdges: inEdges}; 
	}-*/;

	public final native T getValue() /*-{
		return this.Value;
	}-*/;

	public final native ArrayList<Node<T>> getOutEdges() /*-{
		return this.OutEdges;
	}-*/;

	public final native ArrayList<Node<T>> getInEdges() /*-{
		return this.OutInEdges;
	}-*/;
}
