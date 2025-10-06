/*
 * Created on: September 30, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.util.Scanner;

/*
 * Runs and controls the flow of the program
 * 
 * @author Cole Pickley
 */
public class Main {
	/**
	 * Runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Polynomial p1;
		Polynomial p2;
		String input = "";
		boolean programRunning = true;
		boolean inputValid;
		
		printStartScreen();
		while (programRunning) {
			System.out.println();
			System.out.println("Please enter the first polynomial:");
			p1 = new Polynomial(scan.nextLine());
			
			System.out.println();
			System.out.println("Please enter the second polynomial:");
			p2 = new Polynomial(scan.nextLine());
			
			System.out.println();
			System.out.println("The sum is:");
			System.out.println(p1.addPolynomial(p2));
			
			inputValid = false;
			while (!inputValid) {
				System.out.println();
				System.out.println("Would you like to add two more polynomials? (y/n)");
				input = scan.nextLine();
				if (input.equals("n")) {
					programRunning = false;
					inputValid = true;
				}
				else if (input.equals("y")) {
					inputValid = true;
				}
				else
					System.out.println("Invalid Input");
			}
		}
		scan.close();
		System.out.println();
		System.out.println("Thank you for using the Polynomial Addition Program.");
	}
	
	/**
	 * Prints the start screen
	 */
	private static void printStartScreen() {
		System.out.println("Welcome to the Polynomial Addition Program.");
		System.out.println();
		System.out.println("Format rules:");
		System.out.println("1. There must be spaces between elements and operators.");
		System.out.println("2. Coefficients must be specified even if they are 1.");
		System.out.println("3. The negative coefficient of a term should be expressed as such:");
		System.out.println("   '3x^2 - 2x' not '3x^2 + -2x' unless the coeffiecient belongs to");
		System.out.println("   the first term.");
		System.out.println("4. The polynomials should be in order from highest degree to lowest.");
		System.out.println("5. Exponents cannot be negative.");
	}
}
