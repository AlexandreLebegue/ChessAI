package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.Chessboard;
import model.Move;


/**
 * This class runs several tasks to dispatch the possible next movements between CPU cores,
 * so that they can each apply the Minimax algorithm on a subtree of the initial problem
 * @author Camille De Pinho on 2018/12/10
 * @version Last changes on 2018/12/17 at 12h09 by Camille De Pinho
 */
public class MinimaxAI
{
	private static int TIMEOUT_MS = 900; // Un peu moins d'une seconde, pour laisser le temps de combiner les resultats des threads
	
	
	/**
	 * Execute the Minimax algorithm with alpha-beta pruning, to retrieve the best next move
	 * It divides the tree into several subtrees, depending on the number of possible next moves,
	 * and then it creates several tasks to execute on parallel the resulting subtrees
	 * @param chessboard The current state of the chessboard
	 * @return The next move to execute
	 */
	public Move alphaBetaMinimaxSearch(Chessboard chessboard)
	{
		long start = System.nanoTime();
		//System.out.println("Starting Minimax...");
		Move bestMove = runTasks(chessboard, start);
		/*long end = System.nanoTime();
		long time = (end - start) / 1000000; // Nanoseconds to milliseconds
		System.out.println("Finished Minimax in " + time + "ms");*/
		return bestMove;
	}
	
	
	/**
	 * Run several tasks to perform the Minimax algorithm with multithreading
	 * @param chessboard
	 * @return The best next move to perform
	 */
	private Move runTasks(Chessboard chessboard, long startTime)
	{
		ArrayList<Move> allmoves = chessboard.genAllMoves(chessboard.getSideToPlay(), true);
		if (terminalTest(chessboard)) return null; // The game is over, no need to compute anything
		
		// Create an executor service to execute our tasks
		// Cached pool allows us to reuse some threads if possible, which could be useful in future versions to have better performances
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// Dispatch the possible moves between several tasks
		ArrayList<List<Move>> partitions = new ArrayList<List<Move>>();
		int parts = 8;
		for (int i = 0; i < parts; i++) {
		    List<Move> part = allmoves.subList(i * allmoves.size() / parts,
		                                     (i + 1) * allmoves.size() / parts);
		    partitions.add(part);
		}
		

		// Future<> objects will contain the results of the execution of our threads
		List<Future<MinimaxIterationResult>> futureResults = new ArrayList<>();
		
		// Execute the tasks with a 900ms timeout (little less than 1s in order to have enough time to retrieve the result)
		for(List<Move> partition : partitions)
		{
			if(!partition.isEmpty())
			{
				Future<MinimaxIterationResult> ft = executorService.submit(new MinimaxTask(new Chessboard(chessboard, chessboard.getSideToPlay()), partition, 3, executorService));
				futureResults.add(ft);
			}
		}
		
		int bestValue = Integer.MIN_VALUE;
		Move bestMove = null;
		while(true)
		{
			ArrayList<Future<MinimaxIterationResult>> nextResults = new ArrayList<>();
			Iterator<Future<MinimaxIterationResult>> iterator = futureResults.iterator();
			while(iterator.hasNext())
			{
				Future<MinimaxIterationResult> result = iterator.next();
				try
				{
					MinimaxIterationResult minimaxResult = result.get();
					Move move = minimaxResult.getBestMove();
					Integer value = minimaxResult.getBestValue();
					//System.out.println("Current best = " + bestMove + " with " + bestValue + " - Next move is: " + move.toString() + " with value " + value);
					if(value > bestValue)
					{
						bestValue = value;
						bestMove = move;
					}
						
					nextResults.add(minimaxResult.getNextResult());
					iterator.remove();
				}
				catch(InterruptedException | ExecutionException e) { /* Nothing special to do */ }
			}
			futureResults.addAll(nextResults);
			
			if((System.nanoTime() - startTime) / 1000000 < TIMEOUT_MS) // We are running out of time, we have to stop
			{
				executorService.shutdownNow(); // Close the pool and interrupt all running tasks
				
				if (bestMove != null) System.out.println("BEST = " + bestMove.toString() + " with " + bestValue);
				if(bestMove != null)
					return bestMove;
				else // a strange error might have occurred, so we return an arbitrary possible movement to avoid abandoning
					return allmoves.get((int)Math.random() * (allmoves.size()) + 1);
			}
		}
	}
	
	/**
	 * @param chessboard
	 * @return true if there is a checkmate (end of the game), else false
	 */
	protected static boolean terminalTest(Chessboard chessboard)
	{
		return chessboard.isCheckmate();
	}
}
