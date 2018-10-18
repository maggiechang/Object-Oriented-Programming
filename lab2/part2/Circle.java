import java.lang.*;
class Circle {
	final private Point center;
	final private double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}
	public double perimeter() {
		return 2*Math.PI*radius;
	}
}