package parallel;

import java.util.ArrayList;
import java.util.List;

import tools.FileLoader;

public class Main {
	public static void main(String[] args) {

		List<String> lines=new ArrayList<String>();

		for(String arg:args) {
			FileLoader fileLoader=new FileLoader(arg);
			lines.addAll(fileLoader.readWholeFile());

		}
		
		volatile int num=0;








	}

}
