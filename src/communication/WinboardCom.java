package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.ArrayList;

import engine.Engine;
import model.Chessboard;
import model.MinimaxAI;
import model.Move;

public class WinboardCom {

	public static void main(String[] args) throws IOException {

		Engine chessEngine = new Engine();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while(true) // Endless loop
		{
			String command = reader.readLine();

			if(command.equals("protover 2"))
			{
				System.out.println("feature usermove=1");	//append username to move command received from winboard
			}

			if(command.contentEquals("new"))
			{
				chessEngine.stopNoise();
				chessEngine.newGame();
				chessEngine.stopNoise();
			}

			if(command.contentEquals("quit"))
			{
				chessEngine.stopNoise();
				System.exit(0);
			}

			if(command.contentEquals("go"))
			{
				chessEngine.stopNoise();
				chessEngine.play();
				chessEngine.startNoise();
			}

			if(command.startsWith("usermove"))
			{
				chessEngine.stopNoise();
				chessEngine.updateBoard(command);
				chessEngine.play();
				chessEngine.startNoise();
			}
			
			if(command.contentEquals("stop"))
			{
				chessEngine.stopNoise();
			}
		}
	}
}
