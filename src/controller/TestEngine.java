package controller;

import model.Chessboard;
import model.MinimaxAI;

public class TestEngine
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
