package sequential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.FileLoader;

public class Main {
	public static void main(String [] args) {
		
		List<String> lines=new ArrayList<String>();
		
		for(String arg:args) {
			FileLoader fileLoader=new FileLoader(arg);
			lines.addAll(fileLoader.readWholeFile());
			
		}
		
		List<String> words=new ArrayList<String>();
		lines.forEach((line)-> {
			words.addAll(Arrays.asList(line.split("\\s")));});
		
		lines=null;
		
		List<String> trigrams=new ArrayList<String>();
		List<String> bigrams=new ArrayList<String>();
		
			
		
		long timeStart=System.currentTimeMillis();
		
		String trigram=null;
		
		for(String word : words) {
			for(int i=0;i<word.length()-2;i++) {
				trigram=word.substring(i, i+3);
				trigrams.add(trigram);
			}
		}
		
		String bigram=null;
		for(String word: words) {
			for(int i=0;i<word.length()-1;i++) {
				bigram=word.substring(i,i+2);
				bigrams.add(bigram);
			}
		}
		
		long timeEnd=System.currentTimeMillis();
		
		//System.out.println(bigrams.size());
		//System.out.println(trigrams.size());
		System.out.println((timeEnd-timeStart));
		

        
        
    }

}
