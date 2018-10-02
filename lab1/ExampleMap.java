import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ExampleMap
{
   public static List<String> highEnrollmentStudents(
      Map<String, List<Course>> courseListsByStudentName, int unitThreshold)
   {
      List<String> overEnrolledStudents = new LinkedList<>();

      /*
         Build a list of the names of students currently enrolled
         in a number of units strictly greater than the unitThreshold.
      */

      for (Map.Entry <String, List<Course>> val : courseListsByStudentName.entrySet()) {
         String name = val.getKey();
         List<Course> courses = val.getValue();
         int numUnits = 0;
         for(Course course:courses) {
            numUnits += course.getNumUnits();
         }
         if(numUnits > unitThreshold) {
            overEnrolledStudents.add(name);
         }
      }
      return overEnrolledStudents;
      }

            
   }


