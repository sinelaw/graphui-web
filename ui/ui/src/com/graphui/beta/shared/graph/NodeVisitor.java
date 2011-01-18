package com.graphui.beta.shared.graph;

import com.graphui.beta.shared.types.Node;

public interface NodeVisitor<T> {
	void visit(Node<T> node);
}
