import java.util.List;
class Polygon {
	final private List<Point> points;

	public Polygon(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return points;
	}
}