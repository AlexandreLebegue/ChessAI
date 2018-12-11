package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Chessboard;
import model.Move;

public class WinboardCom {
	
	public static void main(String[] args) throws IOException {

		Chessboard chessboard = new Chessboard();
		
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
				  chessboard = new Chessboard();
			  }
			  
			  if(command.contentEquals("quit"))
			  {
				  System.exit(0);
			  }
			  
			  if(command.contentEquals("go"))
			  {
				  ArrayList<Move>  allmoves = chessboard.genAllMoves(chessboard.getSideToPlay());
				  Move move = allmoves.get((int)Math.random() * ( allmoves.size())+1); //TODO Change this with a true AI engine
				  chessboard.moveAChessman(move);
				  System.out.println("move "+chessboard.encodeMove(move));
				  chessboard.nextTurn();
			  }
			  
			  if(command.startsWith("usermove"))
			  {
				  String[] move = command.split(" ");
					 switch (move.length) {
					 
					 case 1:break;
					 case 2:chessboard.moveAChessman(chessboard.decodeMove(move[1]));break;
					 
					 
					 }
					 chessboard.nextTurn();
					 ArrayList<Move>  allmoves = chessboard.genAllMoves(chessboard.getSideToPlay());
					 
					  Move mv = allmoves.get((int)Math.random() * ( allmoves.size())+1); //TODO Change this with a true AI engine
					  chessboard.moveAChessman(mv);
					  System.out.println("move "+chessboard.encodeMove(mv));
					  chessboard.nextTurn();
					  
					  System.out.println("#\n"+chessboard);
			  }

			
			  
			  
			  
			  
			}
	}
}
