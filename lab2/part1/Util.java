import java.lang.*;
public class Util {
	public static double perimeter(Circle circleVal) {
		return 2*Math.PI*circleVal.getRadius();
	}
	public static double perimeter(Rectangle rectangleVal) {
		Point TL = rectangleVal.getTopLeft();
		Point BR = rectangleVal.getBottomRight();
		return (2*(BR.getX() - TL.getX())) + (2*(TL.getY() - BR.getY()));
	}
	public static double perimeter(Polygon polygonVal) {
		Point nextPoint;
		double perimeter = 0.0;

		for (int i = 0; i <polygonVal.getPoints().size(); i++) {
			Point currentPoint = polygonVal.getPoints().get(i);

			if(i < polygonVal.getPoints().size()-1) {
				nextPoint = polygonVal.getPoints().get(i+1);
			}
			else {
				nextPoint = polygonVal.getPoints().get(0);
			}

			double dist = (Math.pow(nextPoint.getX()-currentPoint.getX(), 2)) +  + (Math.pow(nextPoint.getY()-currentPoint.getY(), 2));
			dist = Math.sqrt(dist);

			perimeter += dist;
		}
		return perimeter;
	}
}