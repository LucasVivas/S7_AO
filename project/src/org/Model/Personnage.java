package org.Model;

public class Personnage {
	private int x,y;
	public enum Type{
		GOOD,
		BAD;
	}
	Type type;
	public Personnage(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = Type.GOOD;
	}
	public Personnage(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Type getType() {
		return type;
	}

}
