import java.awt.Point;
import java.awt.*;
import java.util.List;
public class Rectangle implements Shape{
	private double width;
	private double height;
	private Point topLeft;
	private Color color;
	public Rectangle(double width, double height, Point topLeft, Color color) {
		this.width = width;
		this.height = height;
		this.topLeft = topLeft;
		this.color = color;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Point getTopLeft() {
		return topLeft;
	}
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		return width == (((Rectangle)other).width) && height == (((Rectangle)other).height) && topLeft.equals(((Rectangle)other).topLeft) && color.equals(((Rectangle)other).color);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getArea() {
		return height*width;
	}
	public double getPerimeter() {
		return 2*width + 2*height;
	}
	public void translate(Point point) {
		topLeft.setLocation(topLeft.getX() + point.getX(), topLeft.getY() + point.getY());
	}
}
