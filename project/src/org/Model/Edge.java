package org.Model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge> {
	
	public enum Type{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
		}

	private Vertex source;
	private Vertex target;
	private Type type;


	public Edge() {
		super();
		this.type = Type.CORRIDOR;
	}

	public Edge(Type type) {
		super();
		this.type = type ;
	}

	public Edge(Vertex source, Vertex target, Type type) {
		super();
		this.source = source;
		this.target = target;
		this.type = type;
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

	@Override
	public boolean equals(Object obj){
		if (obj.getClass() != Edge.class)
			return false;
		Edge E = (Edge)obj;
		return this.getSource() == E.getSource() && this.getTarget() == E.getTarget();
	}


}
