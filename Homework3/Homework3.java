import java.util.Scanner;

public class Homework3 {

	public static LLQueueADT infixQueue = new LLQueueADT();
	public static LLStackADT operatorStack = new LLStackADT();
	public static LLQueueADT postfixQueue = new LLQueueADT();
	public static LLQueueADT postfixQueue2 = new LLQueueADT();
	public static LLStackADT valueStack = new LLStackADT();

	//method to determine priority of current infix expression token
	public static int infixPriority(char x) {

		switch (x) {

		case '(':
			return 3;

		case '*':
			return 2;

		case '/':
			return 2;

		case '+':
			return 1;

		case '-':
			return 1;

		case ')':
			return 0;

		case '#':
			return 0;

		default:
			return 0;

		}

	}

	//method to determine priority of current operator stack token
	public static int stackPriority(char x) {

		switch (x) {

		case '(':
			return 3;

		case '*':
			return 2;

		case '/':
			return 2;

		case '+':
			return 1;

		case '-':
			return 1;

		case ')':
			return 0;

		case '#':
			return 0;

		default:
			return 0;

		}

	}

	//method to determine whether current token is an operator or an operand
	public static boolean isOperator(char x) {

		switch (x) {

		case '(':
			return true;

		case '*':
			return true;

		case '/':
			return true;

		case '+':
			return true;

		case '-':
			return true;

		case ')':
			return true;

		case '%':
			return true;

		default:
			return false;

		}

	}

	//method to convert an infix expression into a postfix expression
	public static LLQueueADT infixToPostfix(String input) {

		// create infix expression of individual characters to eventually enqueue into
		// infix queue
		char infixExpression;

		// parse char infixExpression and enqueue each character, one by one into infix
		// queue
		for (int outer = 0; outer < input.length(); outer++) {
			infixExpression = input.charAt(outer);
			infixQueue.enqueue(infixExpression);
		}

		// initialize operator stack by pushing '#'
		operatorStack.push('#');

		// dequeue next item from infix while infixQueue still contains items
		while (infixQueue.front != null) {
			char infixDequeue = infixQueue.dequeue();

			// token is an operand? Y or N
			if (!isOperator(infixDequeue)) {

				// token is #
				if (infixDequeue == '#') {

					// pop all entries remaining on the operator stack
					// and enqueue them on postfix
					while (operatorStack.top != null) {
						postfixQueue.enqueue(operatorStack.ontop());
						operatorStack.pop();
					}
				}

				// Y: Enqueue the operand on postfix queue
				postfixQueue.enqueue(infixDequeue);

				// N: Token right parenthesis? Y or N
			} else {

				// Y: pop entries from operator stack and enqueue them on postfix queue
				// until a matching left parenthesis is popped
				if (infixDequeue == ')') {

					while (operatorStack.top != null) {

						// enqueue onto postfix queue
						postfixQueue.enqueue(operatorStack.ontop());

						// pop entries from operator stack
						operatorStack.pop();

						// until matching left parenthesis is popped
						if (operatorStack.ontop() == '(') {
							operatorStack.pop();
							break;
						}

						// disregard the left and right parenthesis

					}

					// N: Pop operator stack and enqueue on postfix operators
					// whose stack priority is >= infix priority of the token,
					// except if '('
				} else {
					if (infixPriority(infixDequeue) > stackPriority(operatorStack.top.getData())) {
						operatorStack.push(infixDequeue);
					} else if (stackPriority(operatorStack.top.getData()) >= infixPriority(infixDequeue)) {
						for (int index2 = 0; index2 < operatorStack.size(); index2++) {
							char popTop = operatorStack.ontop();
							if (popTop == '(') {
								// do nothing
							} else {
								operatorStack.pop();
								postfixQueue.enqueue(popTop);
							}
						}
						operatorStack.push(infixDequeue);
					}
				}
			}
		}

		
		//when the infix queue is empty, pop the rest of the operators off of the operator stack
		//and enqueue them into the postfix queue
		int opStackLength = operatorStack.size();
		for (int index2 = 0; index2 < opStackLength; index2++) {
			if (infixQueue.front == null) {
				char opToPost = operatorStack.ontop();
				operatorStack.pop();
				postfixQueue.enqueue(opToPost);
			} else {
				operatorStack.pop();
			}
		}

		//print out final postfix expression
		System.out.print("Postfix Expression: ");
		while (postfixQueue.front != null && postfixQueue.front() != '#') {
			char postDequeue = postfixQueue.dequeue();
			System.out.print(postDequeue);
			postfixQueue2.enqueue(postDequeue);
		}
		
		//return final postfix expression
		return postfixQueue2;

	}

	// evaluate final postfix expression from infixToPostfix method
	public static int evaluatePostfix(LLQueueADT postfixQueue) {

		
		//while there is still data to be evaluated...
		while (postfixQueue.front != null && postfixQueue.front() != '#') {

			// if token is an operand, push its value on the value stack
			if (!isOperator(postfixQueue.front())) {
				int pushInt = Character.getNumericValue(postfixQueue.front());
				valueStack.push(pushInt);

				// if the token is an operator, pop two values from the stack
				// and apply that operator to them;
				// then push the result back on the stack
			} else {
				valueStack.push(postfixQueue.front());
				char operatorPop = valueStack.pop();
				int num2, num1;
				
				num2 = valueStack.top.getIntData();
				valueStack.pop();
				num1 = valueStack.top.getIntData();
				valueStack.pop();

				switch (operatorPop) {
				
				case '+':
					int result = num1 + num2;
					valueStack.push(result);
					break;
					
				case '-':
					result = num1 - num2;
					valueStack.push(result);
					break;

				case '*':
					result = num1 * num2;
					valueStack.push(result);
					break;

				case '/':
					result = num1 / num2;
					valueStack.push(result);
					break;

				case '%':
					result = num1 % num2;
					valueStack.push(result);
					break;

				}
			}
			postfixQueue.dequeue();
		}
		
		//print final evaluated postfix expression
		System.out.print("\nEvaluated Postfix Expression: ");
		return valueStack.top.getIntData();

	}

	public static void main(String[] args) {

		//scanner to be used to get infix expression input from user
		Scanner scan = new Scanner(System.in);
		
		//prompt user to enter infix expression
		System.out.print("Enter Infix Expression: ");
		String arithmeticExpression = scan.nextLine();
		
		//call infixToPostfix on user input, and evaluatePostfix on that result
		int evaluatedExpression = evaluatePostfix(infixToPostfix(arithmeticExpression));

		//print out the evaluated expression
		System.out.print(evaluatedExpression);
		
		//close scanner to prevent data leak
		scan.close();

	}

}
