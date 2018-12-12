package controller;

import java.util.concurrent.TimeUnit;

import engine.Engine;
import model.Chessboard;
import model.MinimaxAI;

public class TestEngine
{
	public static void main(String[] args)
	{
		
		/*
		 
		
		System.out.println("Launch...");	
		System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
		
		// Test one step of Minimax
		MinimaxAI minimaxAI = new MinimaxAI();
		minimaxAI.alphaBetaMinimaxSearch(chessGame);
		
		System.out.println("End..."); 
		*/	
		System.out.println("begin attack");
		Engine chessEngine = new Engine();

		chessEngine.startNoise();	
		try { TimeUnit.MILLISECONDS.sleep(200); }
		catch (InterruptedException e) { e.printStackTrace(); }
		chessEngine.stopNoise();
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");

		try { TimeUnit.MILLISECONDS.sleep(600); }
		catch (InterruptedException e) { e.printStackTrace(); }
		chessEngine.startNoise();
		try { TimeUnit.MILLISECONDS.sleep(700); }
		catch (InterruptedException e) { e.printStackTrace(); }
		chessEngine.stopNoise();
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
		System.out.println("stop attack");
	}
}
