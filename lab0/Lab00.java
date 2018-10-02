public class Lab00
{
   public static void main(String[] args)
   {
        //declaring and initializing some variables with specific data types
   		int x = 5;
   		String y = "hello";
   		double z = 9.8;

   		//printing the variables
   		System.out.println("x: " + x + " y: "+ y + " z: " + z);
   
   		//a list (make an array in java)
   		int[] nums = {3, 6, -1, 2};
   		for(int i = 0; i < nums.length; i++) {
   			System.out.println(nums[i]);
   		}

   		//call a function
   		System.out.print("Found: ");
   		char_count(y, 'l');


   		//a counting for loop
   		for(int i = 1; i < 11; i++) {
   			System.out.print(i + " ");
   		}

   		System.out.println();

   }
   public static void char_count(String s, char c) {
   		int count = 0;
   		for(int ch = 0; ch < s.length(); ch++) {
   			if(s.charAt(ch) == c) {
   				count++;
   			}
   		}
   		System.out.println(count);
   }
}

