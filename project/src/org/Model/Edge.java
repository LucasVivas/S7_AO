package org.Model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge> {
	int num;

	public Edge() {
		super();
	}
	
	public Edge(int num) {
		super();
		this.num = num;
	}
	
	@Override
	public int compareTo(Edge o) {
		int source = ((Vertex) this.getSource()).compareTo((Vertex) o.getSource());
		if (source!= 0)
			return source;
		else 
		 return ((Vertex) this.getTarget()).compareTo((Vertex) o.getTarget());
	}
	
}
