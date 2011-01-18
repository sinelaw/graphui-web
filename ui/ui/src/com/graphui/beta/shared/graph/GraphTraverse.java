package com.graphui.beta.shared.graph;

import java.util.ArrayList;

import com.graphui.beta.shared.types.Node;

public class GraphTraverse {
	public static <T extends Number> void breadthFirst(ArrayList<Node<T>> graph, Node<T> start, NodeVisitor<T> visitor)
	{
		ArrayList<Node<T>> toVisit = new ArrayList<Node<T>>(graph);
		ArrayList<Node<T>> visited = new ArrayList<Node<T>>();
		initStartNode(start, toVisit);
		while (false == toVisit.isEmpty()) {
			Node<T> node = toVisit.remove(0);
			visited.add(node);
			visitor.visit(node);
			for (Node<T> other : node.getInEdges()) {
				tryAddSingle(toVisit, visited, toVisit.size(), other);
			}
			for (Node<T> other : node.getOutEdges()) {
				tryAddSingle(toVisit, visited, toVisit.size(), other);
			}
		}
	}

	public static <T extends Number> void depthFirst(ArrayList<Node<T>> graph, Node<T> start, NodeVisitor<T> visitor)
	{
		ArrayList<Node<T>> toVisit = new ArrayList<Node<T>>(graph);
		ArrayList<Node<T>> visited = new ArrayList<Node<T>>();
		initStartNode(start, toVisit);
		while (false == toVisit.isEmpty()) {
			Node<T> node = toVisit.remove(0);
			visitor.visit(node);
			for (Node<T> other : node.getInEdges()) {
				tryAddSingle(toVisit, visited, 0, other);
			}
			for (Node<T> other : node.getOutEdges()) {
				tryAddSingle(toVisit, visited, 0, other);
			}
		}
	}

	private static <T> void initStartNode(Node<T> start, ArrayList<Node<T>> toVisit) {
		if (false == toVisit.remove(start)) {
			throw new RuntimeException("Unexpected: start node is not in graph");
		}
		toVisit.add(0, start);
	}

	public static <U> boolean tryAddSingle(ArrayList<U> list, ArrayList<U> testList, int index, U val)
	{
		if (testList.contains(val)) {
			return false;
		}
		list.add(index, val);
		return true;
	}
}
