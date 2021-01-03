package Conditional;

public class RuleEqualTo implements Conditional {
	/**
	 * Returns condition1 == condition2
	 */
	public boolean getResponse(int condition1, int condition2) {
		return condition1 == condition2;
	}
	
	public String toString() {
		return " == ";
	}
}
