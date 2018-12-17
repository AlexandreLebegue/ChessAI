package engine;

import java.util.concurrent.Future;

import model.Move;

/**
 * This class contains the result of a MinimaxTask:
 * - the best move found by the task,
 * - its utility value,
 * - and the result of the next iteration (to perform an iterative deepening process)
 * @author Camille De Pinho on 2018/12/12
 * @version Last changes on 2018/12/17 at 12h36 by Camille De Pinho
 */
public class MinimaxIterationResult
{
	private Move bestMove; // Best move retrieved by this Minimax iteration
	private Integer bestValue; // Value of the best move
	private Future<MinimaxIterationResult> next; // The result of the next iteration of Minimax for the same subtree
	
	public MinimaxIterationResult(Move bestMove, Integer bestValue, Future<MinimaxIterationResult> next)
	{
		this.bestMove = bestMove;
		this.bestValue = bestValue;
		this.next = next;
	}
	
	public Move getBestMove() { return bestMove; }
	public Integer getBestValue() { return bestValue; }
	public Future<MinimaxIterationResult> getNextResult() { return next; }
}
