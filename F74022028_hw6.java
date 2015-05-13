import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class F74022028_hw6 {
	public static void main(String[] args) {
		String input;
		String[] afterSplit;
		Scanner file1 = null;

		try {
			file1 = new Scanner(new FileInputStream("hw6_input.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (file1 != null && file1.hasNextLine()) {
			input = file1.nextLine();
			afterSplit = input.split(" ");
			transform(afterSplit);
		}
	}

	public static void transform(String[] splited) {
		Stack stack = new Stack(splited.length);
		String answer = new String();
		for (int i = 0; i < splited.length; i++) {
			try {
				String s = splited[i];
				switch (s) {
				case "+":
				case "-":
				case "*":
				case "/":
					if (ope(splited[i + 1]) || ope(splited[i + 1])) {
						System.out.println("Invalid expression");
						System.out.println("Invalid expression");
						return;
					}
					while (!stack.isEmpty()
							&& precedence(stack.top().toString()) <= precedence(s)) {
						answer += (" " + stack.pop().toString());
					}
					stack.push(s);
					break;
				case "(":
					if (ope(splited[i + 1])) {
						System.out.println("Invalid expression");
						System.out.println("Invalid expression");
						return;
					}
					stack.push(s);
					break;
				case ")":
					if (ope(splited[i - 1])) {
						System.out.println("Invalid expression");
						System.out.println("Invalid expression");
						return;
					}
					while (!stack.top().toString().equals("(")) {
						answer += (" " + stack.pop().toString());
					}
					stack.pop();
					break;
				default:
					answer += (" " + s);
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid expression");
				System.out.println("Invalid expression");
				return;
			}
		}
		while (!stack.isEmpty()) {
			answer += (" " + stack.pop().toString());
		}
		answer = answer.substring(1);
		System.out.println(answer);
		String[] afterSplit = answer.split(" ");
		evaluate(afterSplit);
	}

	public static boolean ope(String s) {
		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	public static void evaluate(String[] splited) {
		try {
			Stack stack = new Stack(splited.length);
			long answer = 0;
			long left = 0, right = 0;
			if (splited.length == 1) {
				answer = Integer.parseInt(splited[0].toString());
			} else {
				for (int i = 0; i < splited.length; i++) {
					String s = splited[i].toString();
					switch (s) {
					case "+":
						right = Integer.parseInt(stack.pop().toString());
						left = Integer.parseInt(stack.pop().toString());
						answer = (left + right);
						stack.push(answer);
						break;
					case "-":
						right = Integer.parseInt(stack.pop().toString());
						left = Integer.parseInt(stack.pop().toString());
						answer = (left - right);
						stack.push(answer);
						break;
					case "*":
						right = Integer.parseInt(stack.pop().toString());
						left = Integer.parseInt(stack.pop().toString());
						answer = (left * right);
						if((left!=0 && right!=0) && answer==0){
							System.out.println("Overflow");
							return;
						}
						stack.push(answer);
						break;
					case "/":
						right = Integer.parseInt(stack.pop().toString());
						left = Integer.parseInt(stack.pop().toString());
						if (right == 0) {
							System.out.println("Divide by zero");
							return;
						} else {
							answer = left / right;
							stack.push(answer);
						}
						break;
					default:
						stack.push(s);
					}
				}

			}
			if (!stack.isEmpty()) {
				answer = Integer.parseInt((stack.top().toString()));
			}
			if (answer > 2147483647 || answer < -2147483647) {
				System.out.println("Overflow");
			} else {
				System.out.println(answer);
			}
		} catch (Exception e) {
			System.out.println("Overflow");
			return;
		}
	}

	private static int precedence(String string) {
		switch (string) {
		case "+":
		case "-":
			return 6;
		case "*":
			return 5;
		case "(":
			return 100;
		default:
			return 0;
		}
	}
}

class Container {
	protected Object[] elements;
	protected int position;

	public Container(int i) {
		position = -1;
		elements = new Object[i];
	}

	public void add(Object o) {
		position += 1;
		elements[position] = o;
	}

	public Object remove(int i) {
		position -= 1;
		return elements[i];
	}

	public Object get(int i) {
		return elements[i];
	}

	public boolean isEmpty() {
		if (position == -1)
			return true;
		else
			return false;
	}
}

class Stack extends Container {

	public Stack(int i) {
		super(i);
	}

	public void push(Object o) {
		super.add(o);
	}

	public Object pop() {
		return super.remove(position);
	}

	public Object top() {
		return super.get(position);
	}
}
