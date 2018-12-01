package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Chessman {
	
	private String name;
	private String color;
	private int value;
	
	// Mailboxes tab120 and tab64 according to method "mail box" from Robert Hyatt to prevent out of range
	private int[] tab120 =  {1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1,  0,  1,  2,  3,  4,  5,  6,  7, -1,
							-1,  8,  9, 10, 11, 12, 13, 14, 15, -1,
							-1, 16, 17, 18, 19, 20, 21, 22, 23, -1,
							-1, 24, 25, 26, 27, 28, 29, 30, 31, -1,
							-1, 32, 33, 34, 35, 36, 37, 38, 39, -1,
							-1, 40, 41, 42, 43, 44, 45, 46, 47, -1,
							-1, 48, 49, 50, 51, 52, 53, 54, 55, -1,
							-1, 56, 57, 58, 59, 60, 61, 62, 63, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
					
	private int[] tab64 =  {21, 22, 23, 24, 25, 26, 27, 28,
							31, 32, 33, 34, 35, 36, 37, 38,
							41, 42, 43, 44, 45, 46, 47, 48,
							51, 52, 53, 54, 55, 56, 57, 58,
							61, 62, 63, 64, 65, 66, 67, 68,
							71, 72, 73, 74, 75, 76, 77, 78,
							81, 82, 83, 84, 85, 86, 87, 88,
							91, 92, 93, 94, 95, 96, 97, 98};
	
	// Directions vectors according to tab64
	private int[] rookMovements = {-10, 10, -1, 1};
	private int[] bishopMovements = {-11, -9, 11, 9};
	private int[] knightMovements = {-12, -21, -19, -8, 12, 21, 19, 8};
	
	//Constructor
	
	public Chessman(String name, String color) {
		this.name = name;
		this.color = color;
		this.setValue(this.name);
	}
	
	
	//Getters, setters...
	
	private void setValue(String name) {
		ArrayList<String> chessmanNames	= new ArrayList<String>();
		Collections.addAll(chessmanNames, "empty","pawn","knight", "bishop","rook", "queen","king");
		ArrayList<Integer> chessmanValues	= new ArrayList<Integer>();
		Collections.addAll(chessmanValues, 0,1,3, 3,5, 9,0);
		try {value = chessmanValues.get(chessmanNames.indexOf(name));
		} catch (Exception e) {System.out.println("Wrong name inserted: Got "+ name + "\nSet default value to 0 ..." ); 
		value = 0;}
	}	
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	public int getValue(){return value;}

	
	

}
