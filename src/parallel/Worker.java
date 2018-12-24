package parallel;

import java.util.List;

public abstract class Worker extends Thread {
	private List<String> lines;
	private int count;
	
	public Worker(List<String> lines) {
		this.lines=lines;
		count=0;
		
	}
	
	public List<String> getLines() {
		return lines;
	}
	
	public int getCount() {
		return count++;
	}
	
	public void incrementCount() {
		count++;
	}

}
