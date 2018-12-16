package engine;

import java.util.ArrayList;
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
 * @version Last changes on 2018/12/13 at 01h04 by Camille De Pinho
 */
public class MinimaxAI
{
	private static int TIMEOUT_MS = 900; // Un peu moins d'une seconde, pour laisser le temps de combiner les r�sultats des threads
	
	
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
		Move bestMove = runTasks(chessboard, start);//(new Chessboard(chessboard, ourColor));
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
			Future<MinimaxIterationResult> ft = executorService.submit(new MinimaxTask(new Chessboard(chessboard, chessboard.getSideToPlay()), partition, 3, executorService));
			futureResults.add(ft);
		}
		
		int bestValue = Integer.MIN_VALUE;
		Move bestMove = null;
		while((System.nanoTime() - startTime) / 1000000 < TIMEOUT_MS)
		{
			for(Future<MinimaxIterationResult> result : futureResults)
			{
				try
				{
					//System.out.println("Current best = " + bestMove + " with " + bestValue + " - Next move is: " + result.get().getBestMove() + " with value " + result.get().getBestValue());
					if(result.get().getBestValue() > bestValue)
					{
						bestValue = result.get().getBestValue();
						bestMove = result.get().getBestMove();
					}
						
					result = result.get().getNextResult(); // Replace the current Future by the Future of the next iteration
				}
				catch(InterruptedException | ExecutionException e) { /* Nothing special to do */ }
			}
		}
		
		executorService.shutdownNow(); // Close the pool and interrupt all running tasks
				
		//if (bestMove != null) System.out.println("BEST = " + bestMove.toString());
		if(bestMove != null)
			return bestMove;
		else // a strange error might have occurred, so we return an arbitrary possible movement to avoid abandoning
			return allmoves.get((int)Math.random() * (allmoves.size()) + 1);
	}
	
	// TODO: Ajouter test des 3 coups r�p�t�s � la suite (et des 50 coups sans prise?)
	/**
	 * @param chessboard
	 * @return true if there is a checkmate (end of the game), else false
	 */
	protected static boolean terminalTest(Chessboard chessboard)
	{
		return chessboard.isCheckmate();
	}
}
