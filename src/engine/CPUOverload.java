package engine;

import javax.xml.stream.events.StartDocument;

public class CPUOverload extends Thread{
	
	private volatile boolean stopped = true;
	
	public void run()
    {
	
	    while (!stopped)
	    {
	    	double d = Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))))))));
	    	d+=0.0000001;
	    	System.out.println("CPU attack !");
	    }
		
    }
	
	public void stopThread() {
		stopped = true;
	}
	
	public void goThread() {
		stopped = false;
	}

	
}