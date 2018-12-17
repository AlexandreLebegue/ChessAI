package engine;

/**
 * This class is in charge of the CPU overload when the opponent is playing
 */
public class CPUOverload extends Thread
{
	private volatile boolean stopped = true;
	
	public void run()
    {
	    while (!stopped)
	    {
	    	double d = Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))))))));
	    	d+=0.0000001;
	    	System.out.println("CPU attack !" + d);
	    }
    }
	
	public void stopThread() { stopped = true; }
	public void goThread() { stopped = false; }
}
