import java.lang.*;
class Point
{
   final private double x;
   final private double y;

   public Point(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   public double getX()
   {
      return x;
   }

   public double getY()
   {
      return y;
   }

   public double getRadius() {
      double xVal = x * x;
      double yVal = y * y;
      double dist = Math.sqrt((xVal+yVal));
      return dist;
   }
   public double getAngle() {
      double ang = Math.atan2(y, x);
      return ang;
   }

   public Point rotate90() {
      return new Point (-y, x);
   }

}
