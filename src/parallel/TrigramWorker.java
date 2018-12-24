package parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import parallel.Master;

public class TrigramWorker extends Worker {
	public TrigramWorker(List<String> lines) {
		super(lines);
	}
	
	public void run() {
				
		List<String> words=new ArrayList<String>();
		for(String line : super.getLines()) {
			words.addAll(Arrays.asList(line.split("\\s")));
		}
		
		for(String word : words) {
			for(int i=0;i<word.length()-2;i++) 
				super.incrementCount();
		}
	}
	
}
