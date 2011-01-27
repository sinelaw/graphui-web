package com.graphui.beta.shared.graph.visualizers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.graphui.beta.shared.graph.Visualizer;
import com.graphui.beta.shared.types.LayoutShape;
import com.graphui.beta.shared.types.Node;
import com.graphui.beta.shared.types.Node.CopyGraphResult;
import com.graphui.beta.shared.types.Pair;
import com.graphui.beta.shared.types.Vector2D;
import com.hydro4ge.raphaelgwt.client.Raphael.Shape;

public class TreeVisualizer<T> implements Visualizer<T,Shape,Double> {
	protected static double separation = 20.0;
	protected static double size = 30.0;
	
	@Override
	public void UpdateLayout(HashMap<Node<T>, LayoutShape<Shape, Double>> graph) 
	{
		ArrayList<SortedNode<T>> sortedNodes = TopologicalSort(graph.keySet());
		Vector2D<Double> scale 		 = new Vector2D<Double>(size, size);
		Vector2D<Double> pos         = new Vector2D<Double>(0.0, 0.0);
		Vector2D<Double> levelVec    = new Vector2D<Double>(0.0, separation);
		Vector2D<Double> midLevelVec = new Vector2D<Double>(separation, 0.0);
		int lastLevel = 0;
		
		for (SortedNode<T> sortedNode : sortedNodes) {
			int level = sortedNode.getLevel();
			if (level != lastLevel) {
				pos.setX(0.0);
				pos = pos.add(levelVec);
			}
			else {
				pos = pos.add(midLevelVec);
			}

			graph.get(sortedNode.getNode()).setPosition(pos.copy());
			graph.get(sortedNode.getNode()).setScale(scale.copy());
		}
	}


	public static class SortedNode<U> {
		int level;
		Node<U> node;
		public SortedNode(int level, Node<U> node) {
			this.level = level;
			this.node = node;
		}
		public int getLevel() {
			return level;
		}
		public Node<U> getNode() {
			return node;
		}
	}
	
	public static <U> ArrayList<SortedNode<U>> TopologicalSort(Collection<Node<U>> origNodes)
	{
		ArrayList<SortedNode<U>> res = new ArrayList<SortedNode<U>>();
		ArrayList<Pair<Integer,Node<U>>> cur = new ArrayList<Pair<Integer, Node<U>>>();
		ArrayList<Pair<Integer,Node<U>>> out = new ArrayList<Pair<Integer, Node<U>>>();
		CopyGraphResult<U> copyRes = Node.<U>copyGraph(origNodes);
		Collection<Node<U>> copiedNodes = copyRes.getOrigToCopy().values();
		
		for (Node<U> node : copiedNodes) {
			if (node.getInEdges().size() == 0) {
				cur.add(new Pair<Integer,Node<U>>(0, node));
			}
		}
		while (cur.size() > 0) {
			Pair<Integer,Node<U>> pair = cur.remove(cur.size() - 1);
			out.add(pair);
			Integer level = pair.getFirst();
			ArrayList<Node<U>> outNodes = new ArrayList<Node<U>>(pair.getSecond().getOutEdges());
			for (Node<U> outNode : outNodes) 
			{
				outNode.getOutEdges().remove(outNode);
				if (outNode.getInEdges().size() == 0) {
					cur.add(new Pair<Integer,Node<U>>(level, outNode));
				}
			}
		}
		    

		for (Node<U> node : copiedNodes) {
			if (node.getOutEdges().size() > 0 || node.getInEdges().size() > 0) {
				throw new RuntimeException("Cyclic graph can't be sorted");
			}
		}

		for (Pair<Integer,Node<U>> pair : out) {
			res.add(new SortedNode<U>(pair.getFirst(), copyRes.getCopyToOrig().get(pair.getSecond())));
		}
		return res;
	}
}
