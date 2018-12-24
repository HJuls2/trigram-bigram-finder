package parallel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import tools.FileLoader;

public class Master {
	
	public static void main(String[] args) {
		AtomicInteger bigramCounter=new AtomicInteger(0);
		AtomicInteger trigramCounter=new AtomicInteger(0);
		
		List<String> lines=new ArrayList<String>();
		
		for(String arg:args) {
			FileLoader fileLoader=new FileLoader(arg);
			lines.addAll(fileLoader.readWholeFile());

		}
		
		
		long timeStart=System.currentTimeMillis();
		
		TrigramWorker[] trigramWorkers=new TrigramWorker[6];
		BigramWorker[] bigramWorkers=new BigramWorker[6];
		
		int maxNumberxThreads=lines.size()/6;
	
		
		for(int i=0;i<trigramWorkers.length; i++) {
			int end;
			if((i*maxNumberxThreads+maxNumberxThreads) < lines.size())
				end=i*maxNumberxThreads+maxNumberxThreads;
			else 
				end=lines.size()-1;
			
			trigramWorkers[i]=new TrigramWorker(lines.subList(i*maxNumberxThreads, end));
			trigramWorkers[i].start();
			
		}
		
		for(int i=0;i<trigramWorkers.length;i++) {
			try {
				trigramWorkers[i].join();
				trigramCounter.addAndGet(trigramWorkers[i].getCount());
			}
			catch (InterruptedException ie) {}
			
		}
		
		for(int i=0;i<bigramWorkers.length; i++) {
			int end;
			if((i*maxNumberxThreads+maxNumberxThreads) < lines.size())
				end=i*maxNumberxThreads+maxNumberxThreads;
			else 
				end=lines.size()-1;
			
			bigramWorkers[i]=new BigramWorker(lines.subList(i*maxNumberxThreads, end));
			bigramWorkers[i].start();
			
		}
		
		for(int i=0;i<bigramWorkers.length;i++) {
			try {
				bigramWorkers[i].join();
				bigramCounter.addAndGet(bigramWorkers[i].getCount());
			}
			catch (InterruptedException ie) {}
			
		}
				
		
		long timeEnd=System.currentTimeMillis();
		
		System.out.println("### TRIGRAMS ###");
		System.out.println(trigramCounter.get());
		
		System.out.println("### BIGRAMS ###");
		System.out.println(bigramCounter.get());
		
		System.out.println("Time elapsed (milliseconds): "+(timeEnd-timeStart));
		
	}


}
