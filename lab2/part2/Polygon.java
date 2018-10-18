import java.util.List;
class Polygon {
	final private List<Point> points;

	public Polygon(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return points;
	}
	public double perimeter() {
		Point nextPoint;
		double perimeter = 0.0;

		for (int i = 0; i <points.size(); i++) {
			Point currentPoint = points.get(i);

			if(i < points.size()-1) {
				nextPoint = points.get(i+1);
			}
			else {
				nextPoint = points.get(0);
			}

			double dist = (Math.pow(nextPoint.getX()-currentPoint.getX(), 2)) +  + (Math.pow(nextPoint.getY()-currentPoint.getY(), 2));
			dist = Math.sqrt(dist);

			perimeter += dist;
		}
		return perimeter;
	}
}