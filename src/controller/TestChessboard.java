package controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.Chessboard;
import model.Move;

public class TestChessboard {

	public static void main(String[] args)
	{
		System.out.println("Launch...");	
		System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
		
		for(int i = 0; i < 20 ; i++) {
			
			ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay());
			chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));			
			chessGame.nextTurn();
/*
			try { TimeUnit.MILLISECONDS.sleep(200); }
			catch (InterruptedException e) { e.printStackTrace(); }
*/
			
		}
/*		
 * In order to test history after move
 * 
		System.out.println(chessGame);
		chessGame.cancelLastMove();
		System.out.println();
		System.out.println(chessGame);
		ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay());
		chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));	
		System.out.println();
		System.out.println(chessGame);
*/
		
		chessGame.displayHistoryMoves();
		
		System.out.println("End...");		
	}
	
	
}
