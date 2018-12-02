package controller;

import model.Chessboard;
import model.Move;

public class TestChessboard {

	public static void main(String[] args)
	{
		System.out.println("Launch...");	
		System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
		System.out.println();
		System.out.println(chessGame);
		Move testMove = new Move(10, 18);
		System.out.println();
		chessGame.moveAChessman(testMove);
		System.out.println();
		System.out.println(chessGame);
		System.out.println();
		System.out.println("End...");		
	}
	
	
}
