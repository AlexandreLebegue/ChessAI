package controller;

import engine.MinimaxAI;
import model.Chessboard;

public class TestAI
{
	public static void main(String[] args)
	{
		System.out.println("Launch...");	
		System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
		
		// Test one step of Minimax
		MinimaxAI minimaxAI = new MinimaxAI();
		/*Move move = */minimaxAI.alphaBetaMinimaxSearch(chessGame);
		
		System.out.println("End...");		
	}
}
