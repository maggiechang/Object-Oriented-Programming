import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /* some sample tests but you must write more! see lab write up */

   @Test
   public void testCircleGetArea()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);
   }

   @Test
   public void testCircleGetPerimeter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }

   @Test 
   public void testCircleGetColor() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(Color.BLACK, c.getColor());
   }

   @Test
   public void testCircleSetColor() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLUE);
      c.setColor(Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());
   }

   @Test
   public void testCircleTranslate() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLUE);
      c.translate(new Point(1, 2));
      assertEquals(new Point(3, 5), c.getCenter());
   }

   @Test
   public void testCircleGetRadius() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertEquals(5.678, c.getRadius(), DELTA);
   }

   @Test 
   public void testCircleSetRadius() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      c.setRadius(3.2);
      assertEquals(3.2, c.getRadius(), DELTA);
   }

   @Test 
   public void testCircleGetCenter() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertEquals(new Point(2, 3), c.getCenter());
   }

   @Test
   public void testCircleEqual() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle m = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertTrue(c.equals(m));
   }

   @Test 
   public void testRectEqual() {
      Rectangle r = new Rectangle(5, 5, new Point(2, 3), Color.BLACK);
      Rectangle x = new Rectangle(5, 5, new Point(2, 3), Color.BLACK);
      assertTrue(r.equals(x));
      x.setColor(Color.PINK);
      assertFalse(r.equals(x));
   }

   @Test
   public void testRectangleGetArea() {
      Rectangle r = new Rectangle(5, 5, new Point(2, 3), Color.BLACK);
      assertEquals(25, r.getArea(), DELTA);
   }

   @Test
   public void testRectangleGetPerimeter() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      assertEquals(7, r.getPerimeter(), DELTA);
   }

   @Test 
   public void testRectGetColor() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);

      assertEquals(Color.BLACK, r.getColor());
   }

   @Test 
   public void testRectSetColor() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      r.setColor(Color.PINK);
      assertEquals(Color.PINK, r.getColor());
   }

   @Test
   public void testRectTranslate() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      r.translate(new Point(2, 3));
      assertEquals(new Point(4, 6), r.getTopLeft());
   }

   @Test 
   public void testRectSetWidth() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      r.setWidth(5);
      assertEquals(5, r.getWidth(), DELTA);
   }

   @Test 
   public void testrectGetWidth() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      assertEquals(2.5, r.getWidth(), DELTA);
   }

   @Test 
   public void testRectSetHeight() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      r.setHeight(5);
      assertEquals(5, r.getHeight(), DELTA);
   }

   @Test 
   public void testrectGetHeight() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      assertEquals(1, r.getHeight(), DELTA);
   }

   @Test 
   public void testRectGetTopLeft() {
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      assertEquals(new Point(2, 3), r.getTopLeft());
   }

   @Test
   public void testTriangleGetArea() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.BLACK);
      assertEquals(7.5, t.getArea(), DELTA);
   }

   @Test
   public void testTriangleGetPerimeter() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.BLACK);
      assertEquals(13.48418432072729, t.getPerimeter(), DELTA);
   }

   @Test 
   public void testTriGetColor() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.PINK);

      assertEquals(Color.PINK, t.getColor());
   }

   @Test 
   public void testTriSetColor() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      t.setColor(Color.PINK);
      assertEquals(Color.PINK, t.getColor());
   }

   @Test
   public void testTriA() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      assertEquals(new Point(-1, 0), t.getVertexA());
   }

   @Test
   public void testTriB() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      assertEquals(new Point(2, 0), t.getVertexB());
   }

   @Test
   public void testTriC() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      assertEquals(new Point(0, 5), t.getVertexC());
   }

   @Test
   public void testTriTrans() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      t.translate(new Point(5, 5));
      assertEquals(new Point(4, 5), t.getVertexA());
      assertEquals(new Point(7, 5), t.getVertexB());
      assertEquals(new Point(5, 10), t.getVertexC());
   }

   @Test 
   public void testTriEq() {
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.RED);
      Triangle x = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.PINK);
      assertFalse(t.equals(x));
   }

   @Test
   public void testWorkSpaceAreaOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), 
                 Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test
   public void testWorkSpaceGetCircles()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getCircles());
   }

   @Test
   public void testWSAdd() {
      WorkSpace ws = new WorkSpace();
      Shape c =  new Circle(5, new Point(0, 0), Color.BLUE);
      ws.add(c);
      assertEquals(c, ws.get(0));
   }

   @Test 
   public void testWSGet() {
      WorkSpace ws = new WorkSpace();
      Shape c = new Circle(5, new Point(0,0), Color.BLUE);
      Shape c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      ws.add(c);
      ws.add(c2);
      assertEquals(c, ws.get(0));
   }

   @Test
   public void testWorkSpaceSize()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(2, expected.size());
   }

   @Test
   public void testWorkSpaceGetRect()
   {
      WorkSpace ws = new WorkSpace();
      List<Rectangle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);

      ws.add(r);
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(r);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getRectangles());
   }

   @Test
   public void testWorkSpaceGetTri()
   {
      WorkSpace ws = new WorkSpace();
      List<Triangle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);
      Triangle t = new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK);

      ws.add(r);
      ws.add(c1);
      ws.add(t);
      ws.add(c2);

      expected.add(t);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getTriangles());
   }

   @Test
   public void testWorkSpaceGetShapesColor()
   {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Rectangle r = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);
      Triangle t = new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK);

      ws.add(c1);
      ws.add(c2);
      ws.add(r);
      ws.add(t);

      expected.add(c1);
      expected.add(r);
      expected.add(t);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getShapesByColor(Color.BLACK));
   }

   @Test 
   public void testWorkspaceGetPerimeterShapes() {
      WorkSpace ws = new WorkSpace();
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Rectangle r = new Rectangle(2.5, 1, new Point(2, 3), Color.BLACK);
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.BLACK);
      double expected = 35.6759261 + 7 + 13.48418432072729;
      ws.add(c);
      ws.add(r);
      ws.add(t);
      assertEquals(expected, ws.getPerimeterOfAllShapes(), DELTA);
   }

   @Test 
   public void testWorkspaceGetAreaShapes() {
      WorkSpace ws = new WorkSpace();
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Rectangle r = new Rectangle(5, 5, new Point(2, 3), Color.BLACK);
      Triangle t = new Triangle(new Point(-1, 0), new Point(2, 0), new Point(0, 5), Color.BLACK);
      //double expected = 133.7839543;
      ws.add(c);
      ws.add(r);
      ws.add(t);
      assertEquals(133.7839543, ws.getAreaOfAllShapes(), DELTA);
   }

   /* HINT - comment out implementation tests for the classes that you have not 
    * yet implemented */
   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, 
         new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
