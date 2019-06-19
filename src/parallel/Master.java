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
		
		TrigramWorker[] trigramWorkers=new TrigramWorker[numThreads];
		BigramWorker[] bigramWorkers=new BigramWorker[numThreads];
		
		int maxNumberxThreads=lines.size()/numThreads;
	
		
		for(int i=0;i<trigramWorkers.length; i++) {
			int end;
			if((i*maxNumberxThreads+maxNumberxThreads) < lines.size()-maxNumberxThreads)
				end=i*maxNumberxThreads+maxNumberxThreads;
			else 
				end=lines.size();
			
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
			if((i*maxNumberxThreads+maxNumberxThreads) < lines.size()-maxNumberxThreads)
				end=i*maxNumberxThreads+maxNumberxThreads;
			else 
				end=lines.size();
			
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
		
		System.out.println((timeEnd-timeStart));
		
	}


}
