import java.awt.Point;
import java.awt.*;
import java.util.List;
import java.lang.Math;
public class Circle implements Shape {
	private double radius;
	private Point center;
	private Color color;
	public Circle(double radius, Point center, Color color) {
		this.radius = radius;
		this.center = center;
		this.color = color;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Point getCenter() {
		return center;
	}
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		return this.radius == (((Circle)other).radius) && center.equals(((Circle)other).center) && color.equals(((Circle)other).color);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getArea() {
		return Math.PI * radius * radius;
	}
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}
	public void translate(Point point) {
		center.setLocation(center.getX() + point.getX(), center.getY() + point.getY());
	}
	
}