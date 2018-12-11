package model;

public class Move {
	// Attributes
	private int start;
	private int end;
	private String promotion;
	
	// Constructors
	public Move(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public Move(int start, int end, String promotion) {
		super();
		this.start = start;
		this.end = end;
		this.promotion = promotion;
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
	
	@Override
	public boolean equals(Object moveObject)
	{
		Move move = (Move)moveObject; 
		return this.start == move.start && this.end == move.end && this.promotion == move.promotion;
	}
	
	@Override
	public String toString()
	{
		return "[" + start + "-->" + end + "]";
	}
}
