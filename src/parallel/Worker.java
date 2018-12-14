package parallel;

import java.util.List;

public class Worker extends Thread {
	
	private List<String> lines;
	
	public Worker(List <String> lines) {
		this.lines=lines;
	}
	
	public void run() {
		
		
	}

}
