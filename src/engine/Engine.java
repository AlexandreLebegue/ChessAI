package engine;

import java.util.ArrayList;

import model.Chessboard;
import model.Move;

public class Engine {
	
	private Chessboard chessboard;
	private ArrayList<CPUOverload> cpuOverloads = new ArrayList<CPUOverload>();
	private static int NUMBER_OF_THREAD = 64;
	
	
	public Engine() {
		chessboard = new Chessboard();	
	}
	
	/*
	 * Create new game, initialize new chess board
	 */
	public void newGame() {
		chessboard = new Chessboard();

	}
	
	/*
	 * Play one turn
	 */
	public void play() {
		
		MinimaxAI minimaxAI = new MinimaxAI();
		Move move = minimaxAI.alphaBetaMinimaxSearch(chessboard); // Calling the Minimax AI to choose the best move
		
		chessboard.moveAChessman(move);
		chessboard.nextTurn();
		
		System.out.println("move "+chessboard.encodeMove(move));

	}
	
	/*
	 * Update current board with the move of the opponent
	 * @param move to update
	 */
	public void updateBoard(String move) {
		
		String[] mv = move.split(" ");
		switch (mv.length)
		{
			case 1: break;
			case 2: chessboard.moveAChessman(chessboard.decodeMove(mv[1])); break;
			case 4: // castle TODO
		}
		
		chessboard.nextTurn();		
	}
	
	
	/*
	 * Start to do lot of calculation to allow less CPU to the opponent
	 */
	public void startNoise() {
		stopNoise();
		cpuOverloads.clear();
		for(int i = 0; i<NUMBER_OF_THREAD; i++) {
			cpuOverloads.add(new CPUOverload());
		}
		
		for(CPUOverload cpuload : cpuOverloads) {
			cpuload.start();
			cpuload.goThread();
		}
	}
	
	/*
	 * Stop CPU opponent Noise
	 */
	public void stopNoise() {
		for(CPUOverload cpuload : cpuOverloads) {
			cpuload.stopThread();
		}
		
	}
	
}
