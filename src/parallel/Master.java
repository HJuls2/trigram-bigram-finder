package parallel;

import java.util.ArrayList;
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
		
		int[] partialCount=new int[lines.size()];
		
		for(int i=0;i<lines.size();i++) {
			partialCount[i]=0;
			System.out.println(partialCount[i]);
			
		}
		
		
		TrigramWorker[] trigramWorkers=new TrigramWorker[lines.size()];
		
		for(int i=0;i<lines.size();i++) {
			trigramWorkers[i]=new TrigramWorker(lines.get(i));
			trigramWorkers[i].start();
		}
		
		for(int i=0;i<trigramWorkers.length;i++) {
			try {
				trigramWorkers[i].join();
				partialCount[i]=trigramWorkers[i].getCount();
				System.out.println();
			}
			catch (InterruptedException ie) {}
			
		}
		
		for(int i=0;i<lines.size();i++) {
			System.out.println(partialCount[i]);
		}
		
	}


}
