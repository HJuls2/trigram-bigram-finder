package parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import tools.FileLoader;

public class Master {
	
	public static void main(String[] args) {
		
		Map<Integer,AtomicInteger> globalCounters = new HashMap<Integer, AtomicInteger>(2);
		globalCounters.put(2, new AtomicInteger(0));
		globalCounters.put(3,new AtomicInteger(0));
		
		List<String> lines=new ArrayList<String>();
		
		int numThreads=1;
		
		for(String arg:args) {
			if(arg.contains(".txt")) {
				FileLoader fileLoader=new FileLoader(arg);
				lines.addAll(fileLoader.readWholeFile());	
			}
			else {
				numThreads=Integer.parseInt(arg);
			}
			
		}
		
		
		long timeStart=System.currentTimeMillis();
		
		List<KGramWorker> workers = new ArrayList<KGramWorker>();
		
		
		int maxNumberxThreads=lines.size()/numThreads+1;
	
		
		for(int i=0;i<numThreads; i++) {
			int end;
			if((i+1)*maxNumberxThreads < lines.size())
				end=(i+1)*maxNumberxThreads;
			else 
				end=lines.size();
			
			workers.add(new KGramWorker(globalCounters, lines.subList(i*maxNumberxThreads, end)));
			workers.get(i).start();
			
		}
		
		for(KGramWorker worker : workers) {
			try {
				worker.join();
			}
			catch (InterruptedException ie) {}
			
		}
		
		long timeEnd=System.currentTimeMillis();
		
		System.out.println("BIGRAMS: "+globalCounters.get(2));
		System.out.println("TRIGRAMS: "+globalCounters.get(3));
		
		System.out.println((timeEnd-timeStart));
		
	}


}
