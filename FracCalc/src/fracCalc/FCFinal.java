package fracCalc;
import java.util.*;

public class FCFinal {

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
        if (whole1 < 0) {numer1*= -1;} //yay negatives
        
        int[] oprnd2Arr = parseFrac(oprnd2);
        int whole2 = oprnd2Arr[0];
        int numer2 = oprnd2Arr[1];
        int denom2 = oprnd2Arr[2]; 
        numer2 += (absValue(whole2) * denom2); //convert to improper
        if (whole2 < 0) {numer2*= -1;} //yay negatives
        
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
        	return reduceFrac(numerFinal,denom1);
        	
        } else {
        	if(operator.equals("/")){ //if multiplication
        		int denomTemp = denom2;
        		if (numer2 < 0) {
        			denomTemp *= -1;
        		}
        		denom2 = absValue(numer2);
        		numer2 = denomTemp;
        	}
        	int numerFinal = numer1 * numer2;
        	int denomFinal = denom1 * denom2;
        	return reduceFrac(numerFinal,denomFinal);
        }
    }

    //methods to reduce repetitiveness:
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
    
    public static String reduceFrac(int numer, int denom) {
    	//if numerator = 0, return 0
    	if (numer == 0) {
    		return 0 + "";
    	}
    	
    	//simplify fraction
    	int gcf = gcf(numer, denom);
    	if (gcf!=1 && denom!=1) {
    		numer /= gcf;
    		denom /= gcf;
    	}
    	
    	//turn into mixed number and return
    	if (absValue(numer) > denom && denom != 1) {
    		int whole = numer / denom;
    		numer = numer % denom;
    		return whole + "_" + absValue(numer) + "/" + denom;
    	} else if (denom == 1) {
    		return numer + "";
    	} else {
    		return numer + "/" + denom;
    	}
    }
   
    //helper methods:
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

	//returns the greatest common factor of two ints
	public static int gcf(int num1, int num2) {
		int guess = 1;
		for (int i = 1; i <= absValue(num1); i++) {
			if (isDivisibleBy(num1, i) && isDivisibleBy(num2, i) ) {
				guess = i;
			}
		}
		return guess;
	}
	
	//checks for divisibility of two ints. num1 HAS TO BE GREATER than num2
	public static boolean isDivisibleBy (int num1, int num2) {
		num1 = absValue(num1);
		num2 = absValue(num2);
		
		if (num1 == 0 || num2 == 0) {
			throw new IllegalArgumentException("can't divide by 0!");
		}
		if (num1 % num2 == 0) {
			return true;
		} else {
			return false;
		} 

	}
}

