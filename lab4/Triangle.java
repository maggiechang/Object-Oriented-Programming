import java.awt.Point;
import java.awt.*;
import java.util.List;
import java.lang.Math;
public class Triangle implements Shape {
	private Point a;
	private Point b;
	private Point c;
	private Color color;
	public Triangle(Point a, Point b, Point c, Color color) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;
	}
	public Point getVertexA() {
		return a;
	}
	public Point getVertexB() {
		return b;
	}
	public Point getVertexC() {
		return c;
	}
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		return a.equals(((Triangle)other).a) && b.equals(((Triangle)other).b) && c.equals(((Triangle)other).c) && color.equals(((Triangle)other).color);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getArea() {
		double aSide = Math.sqrt((Math.pow(b.getX() - a.getX(), 2))+(Math.pow(b.getY()-a.getY(), 2)));
		double bSide = Math.sqrt((Math.pow(c.getX() - b.getX(), 2))+(Math.pow(c.getY()-b.getY(), 2)));
		double cSide = Math.sqrt((Math.pow(a.getX() - c.getX(), 2))+(Math.pow(a.getY()-c.getY(), 2)));
		double s = (aSide+bSide+cSide)/2;
		return Math.sqrt(s * (s-aSide) * (s-bSide) * (s-cSide));
	}
	public double getPerimeter() {
		double aSide = Math.sqrt((Math.pow(b.getX() - a.getX(), 2))+(Math.pow(b.getY()-a.getY(), 2)));
		double bSide = Math.sqrt((Math.pow(c.getX() - b.getX(), 2))+(Math.pow(c.getY()-b.getY(), 2)));
		double cSide = Math.sqrt((Math.pow(a.getX() - c.getX(), 2))+(Math.pow(a.getY()-c.getY(), 2)));
		return aSide + bSide + cSide;
	}
	public void translate(Point point) {
		a.setLocation(a.getX() + point.getX(), a.getY() + point.getY());
		b.setLocation(b.getX() + point.getX(), b.getY() + point.getY());
		c.setLocation(c.getX() + point.getX(), c.getY() + point.getY());
	}
}