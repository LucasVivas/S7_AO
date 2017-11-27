package org.Model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge> {
	
	public enum Type{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
		} ;
		private Type type;

	public Edge() {
		super();
		this.type = Type.CORRIDOR;
	}
	
	public Edge(Type type) {
		super();
		this.type = type ;
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
