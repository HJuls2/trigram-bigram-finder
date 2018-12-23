package parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import parallel.Master;

public class BigramWorker extends Worker {
	
	public BigramWorker(String line) {
		super(line);
	}
	
	public void run() {
		List<String> words=new ArrayList<String>();
		words.addAll(Arrays.asList(super.getLine().split("\\s")));
		
		for(String word : words) {
			for(int i=0;i<word.length()-2;i++) 
				super.incrementCount();
			
		}
		
		
	}

}
