package fracCalc;
import java.util.*;

public class FCCheckpoint2 {

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

    	//split input into first operand, operator, and second operand
    	String[] inputArr = input.split(" ");
        String oprnd1 = inputArr[0];
        String operator = inputArr[1];
        String oprnd2 = inputArr[2];
        
        //call parseFrac to parse fraction into ints
        int[] oprnd1Arr = parseFrac(oprnd1);
        int whole1 = oprnd1Arr[0];
        int numer1 = oprnd1Arr[1];
        int denom1 = oprnd1Arr[2];  
        
        int[] oprnd2Arr = parseFrac(oprnd2);
        int whole2 = oprnd2Arr[0];
        int numer2 = oprnd2Arr[1];
        int denom2 = oprnd2Arr[2]; 
        
        //construct string to return
        String returnString = "whole:" + whole2 + " numerator:" + numer2 + " denominator:" + denom2;
        return returnString;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need

    public static int[] parseFrac(String oprnd) {
		int whole = 0;
		int numer = 0;
		int denom = 1;
		String[] firstSplit = oprnd.split("_"); 
		if (oprnd.contains("/")) //if there is a fraction
		{ 
			String fraction = firstSplit[firstSplit.length - 1]; //get the last value split
			String[] secondSplit = fraction.split("/"); //split into numer and denom
			numer = Integer.parseInt(secondSplit[0]); //assign 0 to numer
			denom = Integer.parseInt(secondSplit[1]); //assign 1 to denom	
			if (oprnd.contains("_")) 
			{ //if there also is a whole
				whole = Integer.parseInt(firstSplit[0]); //assign first value to whole
			}
		} else 
		{ //if there is no fraction
			whole = Integer.parseInt(firstSplit[0]); //assign first value to whole
		}

		int[] returnArr = {whole, numer, denom};
		return returnArr;
    }
}
