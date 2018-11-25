package fracCalc;
import java.util.*;

public class FCCheckpoint3 {

    public static void main(String[] args) 
    {
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
        numer1 += (absValue(whole1) * denom1); //convert to improper
        if (whole1 < 0) {
        	numer1*= -1;
        }
                
        int[] oprnd2Arr = parseFrac(oprnd2);
        int whole2 = oprnd2Arr[0];
        int numer2 = oprnd2Arr[1];
        int denom2 = oprnd2Arr[2]; 
        numer2 += (absValue(whole2) * denom2); //convert to improper
        if (whole2 < 0) {
        	numer2*= -1;
        }
        
        //evaluate the formula
        if (operator.equals("+") || operator.equals("-")) { //addition or subtraction: change denom
        	int lcm = lcm(denom1, denom2);
        	int multiplier1 = lcm/denom1;
        	int multiplier2 = lcm/denom2;
        	numer1 *= multiplier1;
        	denom1 *= multiplier1;
        	numer2 *= multiplier2;
        	int numerFinal;
        	if(operator.equals("+")) { //if add
        		numerFinal = numer1 + numer2;
        	} else { //if subtract
        		numerFinal = numer1 - numer2;
        	}
        	return numerFinal + "/" + denom1;
        	
        } else {
        	if(operator.equals("/")){ //if multiplication
        		int denomTemp = denom2;
        		denom2 = numer2;
        		numer2 = denomTemp;
        	}
        	int numerFinal = numer1 * numer2;
        	int denomFinal = denom1 * denom2;
        	return numerFinal + "/" + denomFinal;
        }
    }

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

    //finds least common multiple / denominator between two nums
    public static int lcm (int num1, int num2) {
    	int lcm = num1;
    	while (!(lcm % num2 == 0)) {
    		lcm += num1;
    	}
    	return lcm;
    }

    //returns absolute value of a number
	public static int absValue (int number) {
		if (number < 0) {
			return number * -1;
		} else {
			return number;
		}
	}
}
