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
		
		Node<U> thisNode = Node.create(origNode.getValue(), outEdges, inEdges);
		copyingNodes.put(origNode, thisNode);
		
		for (Node<U> outNode : origNode.getOutEdges()) {
			outEdges.add(Node.<U>create(outNode, copyingNodes));
		}
		for (Node<U> inNode : origNode.getInEdges()) {
			inEdges.add(Node.<U>create(inNode, copyingNodes));
		}
		return thisNode;
	}

	protected static <V> List<V> emptyListIfNull(List<V> list) {
		if (null == list) {
			return new ArrayList<V>();
		}
		return list;
	}
	
	public final void connectTo(Node<T> outNode) {
		this.getOutEdges().add(outNode);
		outNode.getInEdges().add(this);
	}
	public final void connectFrom(Node<T> outNode) {
		outNode.connectTo(this);
	}
	public final boolean disconnectOut(Node<T> connectedNode) {
		boolean isConnectedOut = this.getOutEdges().remove(connectedNode);
		if (false == isConnectedOut) {
			return false;
		}
		boolean otherRemoved = connectedNode.getInEdges().remove(this);
		assert otherRemoved;
		return true;
	}
	public final boolean disconnectIn(Node<T> connectedNode) {
		return connectedNode.disconnectOut(this);
	}
	
	public static <U> Node<U> create(U value)
	{
		return create(value, null, null);
	}
	
	public static <U> Node<U> create(U value, List<Node<U>> outEdges, List<Node<U>> inEdges)
	{
		return _create(value, emptyListIfNull(outEdges), emptyListIfNull(inEdges));
	}
	
	public static native <U> Node<U> _create(U value, List<Node<U>> outEdges, List<Node<U>> inEdges)
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
		return this.InEdges;
	}-*/;
}
