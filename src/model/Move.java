package model;

public class Move {

	private String startCase;
	private String endCase;
	private Chessman chessman;
	private Chessboard chessboard;

	public Move(String startCase, String endCase, Chessman chessman, Chessboard chessboard) {
		this.startCase = startCase;
		this.endCase = endCase;
		this.chessman = chessman;
		this.chessboard = chessboard;
	}
	
	
	public String getStartCase() {return startCase;}
	public void setStartCase(String startCase) {this.startCase = startCase;}
	public String getEndCase() {return endCase;}
	public void setEndCase(String endCase) {this.endCase = endCase;}
	public Chessman getChessman() {return chessman;}
	public void setChessman(Chessman chessman) {this.chessman = chessman;}
	public Chessboard getChessboard() {	return chessboard;}
	public void setChessboard(Chessboard chessboard) {this.chessboard = chessboard;}
	
	
}
