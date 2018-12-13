package engine;

import java.util.concurrent.Future;

import model.Move;

/**
 * This class contains the result of a MinimaxTask:
 * - the best move found by the task,
 * - its utility value,
 * - and the result of the next iteration (to perform an iterative deepening process)
 * @author Camille De Pinho on 2018/12/12
 * @version Last changes on 2018/12/13 at 00h49 by Camille De Pinho
 */
public class MinimaxIterationResult
{
	private Move bestMove;
	private Integer bestValue;
	private Future<MinimaxIterationResult> next;
	
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
