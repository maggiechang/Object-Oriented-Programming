import java.awt.Point;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
public class WorkSpace {
	private List<Shape> shapes;
	public WorkSpace() {
		this.shapes = new ArrayList<>();
	}
	public void add(Shape shape) {
		shapes.add(shape);
	}
	public Shape get(int index) {
		return shapes.get(index);
	}
	public int size() {
		return shapes.size();
	}
	public List<Circle> getCircles() {
		List<Circle> circleList = new ArrayList<>();
		for(Shape each:shapes) {
			if (each instanceof Circle) {
				circleList.add((Circle)each);
			}
		}
		return circleList;
	}
	public List<Rectangle> getRectangles() {
		List<Rectangle> rectangleList = new ArrayList<>();
		for(Shape each:shapes) {
			if(each instanceof Rectangle) {
				rectangleList.add((Rectangle)each);
			}
		}
		return rectangleList;
	}
	public List<Triangle> getTriangles() {
		List<Triangle> triangleList = new ArrayList<>();
		for(Shape each: shapes) {
			if(each instanceof Triangle) {
				triangleList.add((Triangle)each);
			}
		}
		return triangleList;
	}
	public List<Shape> getShapesByColor(Color color) {
		List<Shape> colorList = new ArrayList<>();
		for(Shape each:shapes) {
			if(each.getColor().equals(color)) {
				colorList.add(each);
			}
		}
		return colorList;
	}
	public double getAreaOfAllShapes() {
		double totalArea = 0;
		for(Shape each:shapes) {
			totalArea += each.getArea();
		}
		return totalArea;
	}
	public double getPerimeterOfAllShapes() {
		double totalPerm = 0;
		for(Shape each:shapes) {
			totalPerm += each.getPerimeter();
		}
		return totalPerm;
	}
}