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
}