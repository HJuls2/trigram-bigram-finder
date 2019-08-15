package tools;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
	
	
	private String filename;
	
	public FileLoader(String filename){
		this.filename=filename;
	
	}
	
	
	public List<String> readWholeFile() {
		/* USE  text files in "sequential" folder*/
	
		List<String> lowerLines=new ArrayList<String>();
		List<String> lines=null;
		
		try {
			lines= Files.readAllLines(FileSystems.getDefault().getPath("",filename));
		} catch (IOException e) {
			System.out.println("Can't read "+ filename);
		}
		
		lines.forEach( (line) -> {
			if(!line.isBlank() && !line.isEmpty())
				lowerLines.add(line.toLowerCase());
			});
		
		
		
		return lowerLines;
		
	}
	

}
	
	
