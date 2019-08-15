package parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class KGramWorker extends Thread {
	
	private List<String> lines;
	private Map<Integer,AtomicInteger> globalCountersMap;
	private Map<Integer,Integer> localCountersMap;
	
	
	public KGramWorker(Map<Integer,AtomicInteger> globalCountersMap, List<String> lines) {
		this.lines = lines;
		this.globalCountersMap = globalCountersMap;
		
		this.localCountersMap = new HashMap<Integer,Integer>(globalCountersMap.size());
		
		for(Integer kgramSize : globalCountersMap.keySet()) {
			this.localCountersMap.put(kgramSize, 0);
		}
		
	}
	
	public void run() {
		List<String> words = new ArrayList<String>();
		for(String line : this.lines) {
			words.addAll(Arrays.asList(line.split("\\s")));
		}
		
		for(Integer kgramSize : globalCountersMap.keySet()) {
			for(String word : words) {
				for(int i=0;i<=word.length()-kgramSize;i++) {
					this.incrementLocalCounter(kgramSize);
					//System.out.println(word.substring(i, i+kgramSize));
					
				}
								
			}
			globalCountersMap.get(kgramSize).addAndGet(this.localCountersMap.get(kgramSize));
			
		}
	}
	
	private void incrementLocalCounter(Integer key) {
		this.localCountersMap.merge(key, 1, Integer::sum);
	}
	

	public List<String> getLines() {
		return lines;
	}
	

}
