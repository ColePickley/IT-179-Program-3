/*
 * Created on: September 30, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.util.Scanner;

/*
 * Stores polynomial data as Nodes of type Term
 * Adds polynomial objects together
 * 
 * @author Cole Pickley
 */
public class Polynomial {
	private Node termsHead;
	private Node termsTail;
	
	/**
	 * Default constructor for Polynomial
	 */
	public Polynomial () {
		termsHead = null;
		termsTail = null;
	}
	
	/**
	 * Constructor for Polynomial
	 * Takes a String input representing a polynomial
	 * Initializes data appropriately
	 * 
	 * @param polynomial
	 */
	public Polynomial(String polynomial) {
		Scanner scan = new Scanner(polynomial);
		String tempString;
		Node tempNode;
		tempString = scan.next();
		this.addTermToPolynomial(this.getTermOfString(tempString, false));
		tempNode = termsHead;
		while (scan.hasNext()) {
			if (scan.next().equals("+")) {
				tempString = scan.next();
				this.addTermToPolynomial(this.getTermOfString(tempString, false));
			}
			else {
				tempString = scan.next();
				this.addTermToPolynomial(this.getTermOfString(tempString, true));
			}
			tempNode = tempNode.next;
			termsTail = tempNode;
		}
		scan.close();
	}
	
	/**
	 * Takes a Term as an input and adds it to the end of the polynomial
	 * 
	 * @param t
	 */
	public void addTermToPolynomial(Term t) {
		if (termsHead == null) {
			termsHead = new Node(t);
			termsTail = termsHead;
		}
		else {
			termsTail.next = new Node(t);
			termsTail = termsTail.next;
		}
	}
	
	/**
	 * Takes a Polynomial as an input and adds it to this one
	 * Returns the sum of the two polynomials
	 * 
	 * @param p
	 * @return Polynomial
	 */
	public Polynomial addPolynomial(Polynomial p) {
		Node tempNode1 = termsHead;
		Node tempNode2 = p.termsHead;
		Term tempTerm;
		Polynomial output = new Polynomial();
		while (tempNode1 != null || tempNode2 != null) {
			if (tempNode1 == null) {
				output.addTermToPolynomial(tempNode2.termData);
				tempNode2 = tempNode2.next;
			}
			else if (tempNode2 == null) {
				output.addTermToPolynomial(tempNode1.termData);
				tempNode1 = tempNode1.next;
			}
			else {
				tempTerm = tempNode1.termData.addTerm(tempNode2.termData);
				if (tempTerm != null) {
					if (tempTerm.getCoefficient() != 0)
						output.addTermToPolynomial(tempTerm);
					tempNode1 = tempNode1.next;
					tempNode2 = tempNode2.next;
				}
				else if (tempNode1.termData.getExponent() > tempNode2.termData.getExponent()) {
					output.addTermToPolynomial(tempNode1.termData);
					tempNode1 = tempNode1.next;
				}
				else {
					output.addTermToPolynomial(tempNode2.termData);
					tempNode2 = tempNode2.next;
				}
			}
		}
		return output;
	}
	
	/**
	 * Returns the term represented by the inputed String
	 * 
	 * @param s
	 * @param isNegative
	 * @return Term
	 */
	private Term getTermOfString(String s, boolean isNegative) {
		int coefficient;
		int exponent;
		boolean hasX = false;
		if (s.contains("x"))
			hasX = true;
		String temp;
		Scanner scan = new Scanner(s);
		scan.useDelimiter("x");
		coefficient = scan.nextInt();
		if (scan.hasNext()) {
			temp = scan.next();
			exponent = Integer.parseInt(temp.substring(1));
		}
		else if (hasX)
			exponent = 1;
		else
			exponent = 0;
		scan.close();
		if (isNegative)
			return new Term(-1 * coefficient, exponent);
		return new Term(coefficient, exponent);
	}
	
	/**
	 * Returns a String representing the Polynomial
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String output = "";
		if (termsHead != null) {
			Node tempNode = termsHead;
			output += tempNode.termData;
			while (tempNode.next != null) {
				tempNode = tempNode.next;
				if (tempNode.termData.getCoefficient() < 1)
					output += " - " + tempNode.termData.toString().substring(1);
				else
					output += " + " + tempNode.termData;
			}
			return output;
		}
		return "0";
	}
	
	/*
	 * Stores data of type Term
	 * Points to Node next
	 * 
	 * @author Cole Pickley
	 */
	private static class Node {
		private Term termData;
		private Node next;
		
		/**
		 * Constructor for Node
		 * Sets termData to inputed Term
		 * 
		 * @param t
		 */
		private Node(Term termData) {
			this.termData = termData;
		}
		
		/**
		 * Constructor for Node
		 * Sets termData to inputed Term
		 * Sets next to inputed Node
		 * 
		 * @param t
		 * @param next
		 */
		private Node(Term termData, Node next) {
			this.termData = termData;
			this.next = next;
		}
	}
}
