package parallel;

public abstract class Worker extends Thread {
	private String line;
	private int count;
	
	public Worker(String line) {
		this.line=line;
		count=0;
		
	}
	
	public String getLine() {
		return line;
	}
	
	public int getCount() {
		return count++;
	}
	
	public void incrementCount() {
		count++;
	}

}
