package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WinboardCom {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
			while(true) // Endless loop
			{
			  String command = reader.readLine();

			  if(command.equals("something"))
			  {
			    //doSomething();
			  }
			  
			  
			}
	}
}
