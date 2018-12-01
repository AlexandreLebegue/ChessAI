package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Chessman {
	
	private String name;
	private String color;
	private int value;
	
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
