package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import model.Chessboard;
import model.Chessman;
import model.Move;

/**
 * This class represents a Task performing an iterative deepening version of
 * the Minimax algorithm with alpha-beta pruning, to determine the best move to play next
 * The iterative deepening process ensures us to have a best move to retrieve after timeout,
 * no matter how deep we went in the Minimax tree
 * @author Camille De Pinho on 2018/12/12
 * @version Last changes on 2018/12/13 at 01h04 by Camille De Pinho
 */
public class MinimaxTask implements Callable<MinimaxIterationResult>
{
	private static int BONUS_CHECKMATE = 1000;
	private static int MALUS_CHECKMATE = -1000;
	private int iterativeDeepeningLimit; // Limit for the iterative deepening process
	
	private Chessboard chessboard;
	private List<Move> firstPossibleMoves;
	private int depth = -1; // Current depth of the Minimax tree
	private final ExecutorService executorService; // ExecutorService, which will be used to start the next iteration
	
	// Memorize our color and the color of the opponent (which will be used in the utility function)
	private String ourColor;
	private String opponentColor;
	
	// Keep a trace of the best move found so far, and its utility value
	private Move[] currentBestMoves;
	private Integer currentBestValue;
	
	
	public MinimaxTask(Chessboard chessboard, List<Move> moves, int maximumDepth, ExecutorService xs)
	{
		this.chessboard = chessboard;
		this.firstPossibleMoves = moves;
		this.iterativeDeepeningLimit = maximumDepth;
		this.currentBestMoves = new Move[iterativeDeepeningLimit];
		ourColor = chessboard.getSideToPlay();
		opponentColor = chessboard.oppositeColor(ourColor);
		this.executorService = xs;
	}
	
	@Override
	public MinimaxIterationResult call() throws Exception
	{
		currentBestValue = firstMaxValue(chessboard, Integer.MIN_VALUE, Integer.MAX_VALUE);
		/*System.out.println("For task " + this.toString() + " - Best moves are :");
		for(int i=0 ; i<currentBestMoves.length - 1 ; i++)
		{
			if(currentBestMoves[i] != null) System.out.println("#" + i + " " + currentBestMoves[i].toString());
		}
		System.out.println("#");*/
		return new MinimaxIterationResult(this.currentBestMoves[0], this.currentBestValue,
			executorService.submit(new MinimaxTask(chessboard, firstPossibleMoves, iterativeDeepeningLimit + 1, executorService))); // Next iteration of iterative deepening
	}
	
	
	/**
	 * This method is the same as maxValue, excepted that she does not start with 
	 * all possible moves for the current chessboard, but only with the moves
	 * this parallel task has been associated to
	 * @param chessboard
	 * @param alpha
	 * @param beta
	 * @return The best utility value
	 */
	private int firstMaxValue(Chessboard chessboard, int alpha, int beta)
	{
		depth++;
		/*System.out.println("#\nDepth = " + depth + " - MAX");
		System.out.println("#" + chessboard.toString() + "\n");*/
		
		// Here we do not compute all possible moves, we start from the partial list of moves that this task has been associated to
		if (Thread.currentThread().isInterrupted() || MinimaxAI.terminalTest(chessboard) || (depth >= iterativeDeepeningLimit-1)) { depth--; return utility(chessboard); }
		int utilityValue = Integer.MIN_VALUE;
		
		for (Move possibleMove : firstPossibleMoves)
		{
			if(Thread.currentThread().isInterrupted()) break;
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
				currentBestMoves[depth] = possibleMove;
				//System.out.println("#MAX - Changed best move at depth " + depth + " : " + possibleMove.toString());
			} // else alpha's value does not change
			
			//System.out.println("#In MAX alpha-beta for depth " + depth + " and move " + possibleMove.toString() + " : alpha=" + alpha + " ; beta=" + beta);
		}
		depth--;
		return utilityValue;
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
		if (Thread.currentThread().isInterrupted() || MinimaxAI.terminalTest(chessboard) || (depth >= iterativeDeepeningLimit-1)) { depth--; return utility(chessboard); }
		int utilityValue = Integer.MIN_VALUE;
		
		for (Move possibleMove : allmoves)
		{
			if(Thread.currentThread().isInterrupted()) break;
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
				currentBestMoves[depth] = possibleMove;
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
		if (Thread.currentThread().isInterrupted() || MinimaxAI.terminalTest(chessboard) || (depth >= iterativeDeepeningLimit-1)) { depth--; return utility(chessboard); }
		int utilityValue = Integer.MAX_VALUE;
		
		for (Move possibleMove : allmoves)
		{
			if(Thread.currentThread().isInterrupted()) break;
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
				currentBestMoves[depth] = possibleMove;
				//System.out.println("#MIN - Changed best move at depth " + depth + " : " + possibleMove.toString());
			} // else beta's value does not change
			
			//System.out.println("#In MIN alpha-beta for depth " + depth + " and move " + possibleMove.toString() + " : alpha=" + alpha + " ; beta=" + beta);
		}
		depth--;
		return utilityValue;
	}
	
	
	/**
	 * Computes the utility of a chessboard, taking into account:
	 * - the weights of the pieces on the board
	 * - the possibilities of rooking
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
	
	public Move getBestMove() { return this.currentBestMoves[0]; }
	public int getBestValue() { return this.currentBestValue; }
	
}