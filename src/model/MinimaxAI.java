package model;

import java.util.ArrayList;

/**
 * This class performs the Minimax algorithm with alpha-beta pruning,
 * to determine the best move to play next
 * @author Camille De Pinho on 2018/12/10
 * @version Last changes on 2018/12/12 at 00h06 by Camille De Pinho
 */
public class MinimaxAI
{
	/*
	 * TODO RESTE A FAIRE:
	 * - Ajouter un cutoff de temps (un peu moins d'1s)
	 * - Ajouter multithreading
	 * - Améliorer la fonction d'utilité (prendre en compte de nouveaux paramètres)
	 * - Si besoin, optimiser/améliorer l'implémentation de la Triangular PV Table
	 */
	
	private static int BONUS_CHECKMATE = 1000;
	private static int MALUS_CHECKMATE = -1000;
	
	public static int MAX_STEPS = 4; // For test purposes
	private int depth = -1; // Current depth of the Minimax tree
	
	// Memorize our color and the color of the opponent (which will be used in the utility function)
	private String ourColor;
	private String opponentColor;

	private Move[] bestMoves = new Move[MAX_STEPS]; // "Simplified" implementation of the Triangular PV Table
	
	
	/**
	 * Execute the Minimax algorithm with alpha-beta pruning, to retrieve the best next move
	 * @param chessboard The current state of the chessboard
	 * @return The next move to execute
	 */
	public Move alphaBetaMinimaxSearch(Chessboard chessboard)
	{
		ourColor = chessboard.getSideToPlay();
		opponentColor = chessboard.oppositeColor(ourColor);
		long start = System.nanoTime();
		//System.out.println("#Starting Minimax...");
		/*int utilityValue = */maxValue(new Chessboard(chessboard, ourColor), Integer.MIN_VALUE, Integer.MAX_VALUE);
		long end = System.nanoTime();
		long time = (end - start) / 1000000; // Nanoseconds to milliseconds
		/*System.out.println("#Finished Minimax in " + time + "ms - Tree of best moves");
		for(int i=0 ; i<bestMoves.length - 1 ; i++)
		{
			if(bestMoves[i] != null) System.out.println("#" + i + " " + bestMoves[i].toString());
		}
		System.out.println("#");*/
		return bestMoves[0];
		
		/* INFO: niveau timing, en single-threaded, on est à environ:
		 * - une demi-seconde pour descendre jusqu'au niveau 4 de l'arbre d'exploration
		 * - 5 secondes pour descendre jusqu'au niveau 5
		 */
	}
	
	/**
	 * max_value function for Minimax algorithm
	 * @param chessboard
	 * @param alpha
	 * @param beta
	 * @return Max value
	 */
	private int maxValue(Chessboard chessboard, int alpha, int beta)
	{
		depth++;
		/*System.out.println("#\nDepth = " + depth + " - MAX");
		System.out.println("#" + chessboard.toString() + "\n");*/
		
		ArrayList<Move> allmoves = chessboard.genAllMoves(chessboard.getSideToPlay(), true); // Also updates the checkmate boolean, that is why we compute it here
		if (terminalTest(chessboard) || (depth >= MAX_STEPS-1)) { depth--; return utility(chessboard); }
		int utilityValue = Integer.MIN_VALUE;
		
		for (Move possibleMove : allmoves)
		{
			//System.out.println("#MAX - Studied move = " + possibleMove.toString() + " - calling MINVALUE");
			chessboard.moveAChessman(possibleMove);
			chessboard.nextTurn();
			int min = minValue(chessboard, alpha, beta);
			utilityValue = Math.max(utilityValue, min);
			//System.out.println("#Cancel move: ");
			chessboard.cancelLastMove();
			
			if(utilityValue > beta) { depth--; return utilityValue; }
			if(utilityValue > alpha)
			{
				alpha = utilityValue;
				// Update the best move for this depth
				bestMoves[depth] = possibleMove;
				//System.out.println("#MAX - Changed best move at depth " + depth + " : " + possibleMove.toString());
			} // else alpha's value does not change
			
			//System.out.println("#In MAX alpha-beta for depth " + depth + " and move " + possibleMove.toString() + " : alpha=" + alpha + " ; beta=" + beta);
		}
		depth--;
		return utilityValue;
	}
	
	/**
	 * min_value function for Minimax algorithm
	 * @param chessboard
	 * @param alpha
	 * @param beta
	 * @return Min value
	 */
	private int minValue(Chessboard chessboard, int alpha, int beta)
	{
		depth++;
		/*System.out.println("#\nDepth = " + depth + " - MIN");
		System.out.println("#" + chessboard.toString() + "\n");*/
		ArrayList<Move> allmoves = chessboard.genAllMoves(chessboard.getSideToPlay(), true); // Also updates the checkmate boolean, that is why we compute it here
		if (terminalTest(chessboard) || (depth >= MAX_STEPS-1)) { depth--; return utility(chessboard); }
		int utilityValue = Integer.MAX_VALUE;
		
		for (Move possibleMove : allmoves)
		{
			//System.out.println("#MIN - Studied move = " + possibleMove.toString() + " - calling MAXVALUE");
			chessboard.moveAChessman(possibleMove);
			chessboard.nextTurn();
			int max = maxValue(chessboard, alpha, beta);
			utilityValue = Math.min(utilityValue, max);
			//System.out.println("#Cancel move: ");
			chessboard.cancelLastMove();
			
			if(utilityValue < alpha) { depth--; return utilityValue; }
			
			if(utilityValue < beta)
			{
				beta = utilityValue;
				// Update the best move for this depth
				bestMoves[depth] = possibleMove;
				//System.out.println("#MIN - Changed best move at depth " + depth + " : " + possibleMove.toString());
			} // else beta's value does not change
			
			//System.out.println("#In MIN alpha-beta for depth " + depth + " and move " + possibleMove.toString() + " : alpha=" + alpha + " ; beta=" + beta);
		}
		depth--;
		return utilityValue;
	}
	
	
	
	// TODO: Améliorer cette fonction
	// (Pour l'instant, elle ne tient compte que des poids des pions restants sur le plateau)
	/**
	 * Computes the utility of a chessboard, taking into account:
	 * - the weights of the pieces on the board
	 * - [TO BE CONTINUED...]
	 * @param chessboard
	 * @return The utility of this board
	 */
	public int utility(Chessboard chessboard)
	{
		int utility = 0;
		
		// Counting the weights of the pieces currently on the board
		Chessman[] pieces = chessboard.getCells();
		for(Chessman piece : pieces)
		{
			String pieceName = piece.getName();
			String pieceColor = piece.getColor();
			int pieceValue = piece.getValue();
			if(pieceName.equals("empty")) continue;
			
			int sign = 1;
			if(pieceColor.equals(opponentColor)) sign = -1;
			utility += (pieceValue * sign);
		}
		
		// Put a big bonus for checkmate on the opponent, and big penalty for checkmate on us
		ArrayList<Move> opponentMoves = chessboard.genAllMoves(opponentColor, true);
		if(opponentMoves.isEmpty()) utility += BONUS_CHECKMATE; // Checkmate in our favor!
		if(chessboard.isCheckmate()) utility += MALUS_CHECKMATE; // Checkmate in favor of the opponent!
		
		// Put a bonus if we can rook
		if (ourColor == "white") {
			if (chessboard.isRookCanCastle63() == true) utility += 1;
			if (chessboard.isRookCanCastle0() == true) utility += 1;
		}
		else {
			if (chessboard.isRookCanCastle56() == true) utility += 1;
			if (chessboard.isRookCanCastle7() == true) utility += 1;
		}
		
		/*System.out.println("Utility for chessboard - " + chessboard.getSideToPlay() + " playing");
		System.out.println(chessboard.toString());
		System.out.println(utility);*/
		
		return utility;
	}
	
	

	// TODO: retourner vrai si "échec et mat" pour noir OU blanc, sinon faux
	/**
	 * @param chessboard
	 * @return true if there is a checkmate (end of the game), else false
	 */
	private boolean terminalTest(Chessboard chessboard)
	{
		 return chessboard.isCheckmate();
	}
	
	
	/*private Move chooseBestMove(int bestUtilityValue)
	{
		
	}*/
}
