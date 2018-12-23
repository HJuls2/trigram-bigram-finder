package parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import parallel.Master;

public class TrigramWorker extends Worker {
	public TrigramWorker(String line) {
		super(line);
	}
	
	public void run() {
		List<String> words=new ArrayList<String>();
		words.addAll(Arrays.asList(super.getLine().split("\\s")));
		
		for(String word : words) {
			for(int i=0;i<word.length()-2;i++) 
				super.incrementCount();
		}
		try {
			Thread.sleep(5000);
			System.out.println(super.getLine() +" has been analyzided, found "+super.getCount()+" trigrams");
			
		}catch (InterruptedException ie) {}
		
	}
	
}
