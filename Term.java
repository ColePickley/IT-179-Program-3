/*
 * Created on: September 30, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

/*
 * Object representing a term within a polynomial
 * Contains the coefficient and exponent of the term
 * Can add two terms together
 * 
 * @author Cole Pickley
 */
public class Term {
	private int coefficient;
	private int exponent;
	
	/**
	 * Constructor for Term
	 * Initializes variables to corresponding parameters
	 * 
	 * @param coefficient
	 * @param exponent
	 */
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	/**
	 * Returns coefficient
	 * 
	 * @return int
	 */
	public int getCoefficient() {
		return coefficient;
	}
	
	/**
	 * Returns exponent
	 * 
	 * @return int
	 */
	public int getExponent() {
		return exponent;
	}
	
	/**
	 * Adds the inputed term to this one
	 * Returns the sum of the two terms
	 * 
	 * @param t
	 * @return Term
	 */
	public Term addTerm(Term t) {
		if (t.getExponent() == this.exponent)
			return new Term(t.getCoefficient() + this.coefficient, this.exponent);
		return null;
	}
	
	/**
	 * Returns a String representing the Term
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		if (exponent == 0)
			return String.valueOf(this.coefficient);
		else if (exponent == 1)
			return this.coefficient + "x";
		return this.coefficient + "x^" + this.exponent;
	}
}
