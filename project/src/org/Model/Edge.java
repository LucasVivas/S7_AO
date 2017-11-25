package org.Model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge>,Cloneable {
	private Vertex source;
	private Vertex target;

	public Edge() {
		super();
	}
	
	public Edge(Vertex source, Vertex target) {
		super();
		this.source = source;
		this.target = target;
	}


	
	@Override
	public int compareTo(Edge o) {
		int source = ((Vertex) this.getSource()).compareTo((Vertex) o.getSource());
		if (source!= 0)
			return source;
		else 
		 return ((Vertex) this.getTarget()).compareTo((Vertex) o.getTarget());
	}

	@Override
	public Vertex getTarget() {
		return target;
	}

	@Override
	public Vertex getSource() {
		return source;
	}

	public Edge clone(){
		Edge E = (Edge) super.clone();
		return E;
	}

	public String toString(){
		return super.toString();
	}

/*	@Override
	public boolean equals(Object obj){
		if (obj.getClass() != Edge.class)
			return false;
		Edge E = (Edge)obj;
		return this.getSource() == E.getSource() && this.getTarget() == E.getTarget();
	}
	*/
}
