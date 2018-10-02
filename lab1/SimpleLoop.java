class SimpleLoop
{
   public static int sum(int low, int high)
   {
      /* TO DO:  Return the sum of the integers between
         low and high (inclusive).  Yes, this can be
         done without a loop, but the point is to
         practice the syntax for a loop.
      */
      int finalValue = 0;
      for(int val = low; val<=high; val++) {
      	finalValue += val;
      }
      return finalValue;
   }
}
