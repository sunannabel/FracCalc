package fracCalc;
import java.util.*;

public class FCCheckpoint1 {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	boolean done = false;
    	Scanner userInput = new Scanner(System.in);
    	while (!done) {
    		System.out.print("Enter your equation or type 'quit' to quit. ");
    		String input = userInput.nextLine();
    		if (input.equals("quit")) {
    			done = true;
    		} else {
    			System.out.println(produceAnswer(input));
    		}
    	}	
    	userInput.close();
    	
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
       
    	String[] inputArr = input.split(" ");
        String firstOprd = inputArr[0];
        String operator = inputArr[1];
        String secondOprd = inputArr[2];
    	return secondOprd;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need

}
