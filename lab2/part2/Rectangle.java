import java.lang.*;
class Rectangle {
	final private Point TopLeft;
	final private Point BottomRight;

	public Rectangle(Point TopLeft, Point BottomRight) {
		this.TopLeft = TopLeft;
		this.BottomRight = BottomRight;
	}

	public Point getTopLeft() {
		return TopLeft;
	}

	public Point getBottomRight() {
		return BottomRight;
	}
	public double perimeter() {
		Point TL = TopLeft;
		Point BR = BottomRight;
		return (2*(BR.getX() - TL.getX())) + (2*(TL.getY() - BR.getY()));
	}
}