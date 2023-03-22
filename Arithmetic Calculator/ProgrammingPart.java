import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProgrammingPart {

    public static double calculator(String expression) {
    	
        ArrayStack<Double> operands = new ArrayStack<Double>();
        ArrayStack<Character> operators = new ArrayStack<Character>();

        for (int i = 0; i < expression.length(); i++) {

        	char c = expression.charAt(i);
            
            if (Character.isDigit(c)) {
                double num = 0;
                
                while (Character.isDigit(c)) {
                	
                    num = num * 10 + (c - '0');
                    
                    i++;
                    if (i < expression.length())
                        c = expression.charAt(i);
                    else
                        break;
                }i--;
                
                operands.push(num);
            } 
            
            else if (c == '(') {
                operators.push(c);
            } 
            
            else if (c == ')') {
                while (operators.top() != '(') {
                	
                    double result = evaluator(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.pop();
            } 
            
            else if (isOperator(c)) {
            	
            	if (isOperator(expression.charAt(i+1))) {
            		if (c == '<' && expression.charAt(i+1) == '=') {c = '$';}
            		else if (c == '>' && expression.charAt(i+1) == '=') {c = '@';}
            		i++;
            	}
            	
                while (!operators.isEmpty() && hasPrecedence(c, operators.top())) {
                
                    double result = evaluator(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                    
                }
                operators.push(c);
            } 
        }

        while (!operators.isEmpty()) {
            double result = evaluator(operators.pop(), operands.pop(), operands.pop());
            operands.push(result);
        }

        return operands.pop();
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '<' || c == '>' || c == '=' || c == '!';
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')' || op1 == '^')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 != '^' || op2 != '*' || op2 != '/'))
            return false;
        if ((op1 == '+' || op1 == '-') && (op2 == '<' || op2 == '>' || op2 == '@' || op2 == '$' || op2 == '=' || op2 == '!'))
            return false;
        if ((op1 == '<' || op1 == '>' || op1 == '@' || op1 == '$') && (op2 == '=' || op2 == '!'))
            return false;
        return true;
    }

    public static double evaluator(char operator, double operand2, double operand1) {
        if (operator=='+') {return operand1 + operand2;}
        else if (operator=='-') {return operand1 - operand2;}
        else if (operator=='*') {return operand1 * operand2;}
        else if (operator=='/') {return operand1 / operand2;}
        else if (operator=='^') {return Math.pow(operand1, operand2);}
        else if (operator=='<') {return operand1 < operand2 ? 1.10101 : 0.10101;}
        else if (operator=='>') {return operand1 > operand2 ? 1.10101 : 0.10101;}
        else if (operator=='$') {return operand1 <= operand2 ? 1.10101 : 0.10101;}
        else if (operator=='@') {return operand1 >= operand2 ? 1.10101 : 0.10101;}
        else if (operator=='=') {return operand1 == operand2 ? 1.10101 : 0.10101;}
        else if (operator=='!') {return operand1 != operand2 ? 1.10101 : 0.10101;}
        return 0;
    }
    
    /* Used true = 1.10101 and false = 0.10101 instead of the usual binary 1 and 0 
     * as they are common results of arithmetic evaluations whereas 1.10101 and 
     * 0.10101 have a very less chance of coming out as results of arithmetic evals. 
     * To do: Better approach would be for operand stack to store String values and 
     * use Double.ParseDouble() while performing calculation so that boolean values 
     * are easier to store. 
     * Overall time complexity of calculator = O(n)
     */
            
    
    public static void main(String[] args) {
    	
    	PrintWriter pw = null;
    	Scanner kb = null;
		
		try{
			pw = new PrintWriter(new FileOutputStream("TestLogs.txt"));
			kb = new Scanner(new FileInputStream("SampleExpression.txt"));
		}catch(FileNotFoundException e) {	
			System.out.println("Could not open/create the files to write to. "
					+ " Please try again later.");
			System.out.print("Program will terminate.");
			System.exit(0);			   		   
		}
		
		
        String expression;
        double resCalculator;
        
        while (kb.hasNextLine()) {
        	
        	expression = kb.nextLine();
        	
        	resCalculator = calculator(expression);
            if (resCalculator==1.10101) {pw.println(expression + " = " + true);}
            else if (resCalculator==0.10101) {pw.println(expression + " = " + false);}
            else {pw.println(expression + " = " + resCalculator);}
        	
        }
        
        System.out.println("ALL EXPRESSIONS CALCULATED SUCCESSFULLY CHECK TestLogs.txt");
        
        pw.close();
        kb.close();
    }
}
