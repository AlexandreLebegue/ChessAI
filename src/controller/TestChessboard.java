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
		
		for(int i = 0; i < 450 ; i++) {
			
			ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay());
			chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));
			
			System.out.println();	
			System.out.println(chessGame);	
			System.out.println();
			
			chessGame.nextTurn();

			try { TimeUnit.MILLISECONDS.sleep(500); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		
		System.out.println("End...");		
	}
	
	
}
