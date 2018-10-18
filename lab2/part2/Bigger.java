import java.util.*;
class Bigger {
	public static double whichIsBigger(Circle circleVal, Rectangle rectVal, Polygon polyVal) {
		double c = circleVal.perimeter();
		double r = rectVal.perimeter();
		double p = polyVal.perimeter();

		ArrayList<Double> perimeterVals = new ArrayList<Double>();
		perimeterVals.add(c);
		perimeterVals.add(r);
		perimeterVals.add(p);

		double max = perimeterVals.get(0);
		for(int i=0; i < perimeterVals.size();i++) {
			if(perimeterVals.get(i) > max) {
				max = perimeterVals.get(i);
			}
		}
		return max;
	}
}