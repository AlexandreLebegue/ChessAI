package model;

public class Move {
	// Attributes
	private int start;
	private int end;
	private String promotion;
	
	// Constructor
	public Move(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	// Getters and setters
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	
		
}
