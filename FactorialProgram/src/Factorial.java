
public class Factorial {
	static int loopfact(int num) //memory efficient, simple, iterative.
	{
		int fact = 1 ;// local var 'fact' stored in a one stack frame 
		for (int i = num ; i >= 1 ; i--) {
			 // fact multiplied by i iteratively and value updated at that stack frame only 
			fact = fact * i;
		}
		return fact;//fact value is returned and cove out of stack and goes to main()
	}
	
	
	
	static int recursionfact(int num)  //elegant, but multiple stacks use more memory.
	{
		  //base condion - otherwise stackoverflow error
		if(num==0 || num==1) {
			return 1; //return value stored in stack
		}
		else {
			  // recursive call 
			  // for every call num will get stored in a new stack frame 
			return num*recursionfact( num- 1);
		}
	}
	
	

	public static void main(String[] args) {
		  // step 1 - JVM calls the main method and then num 5 in initialized in memory
		int num = 5;
		  // step 2 - loopfact(num) is call - loop run , fact calculate , result print 
		System.out.println("factorial of " + num +" using loop is " + loopfact(num));
		 //step 3 - fact call through Recursive calls , then result print 
		System.out.println("factorial of " + num +" using recursion is " +recursionfact(num));
         // main() execute complete - program terminate
	}
}
