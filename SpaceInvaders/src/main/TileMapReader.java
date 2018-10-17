package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class TileMapReader {
	//File file;
	InputStream inStream;
	InputStreamReader reader;
	BufferedReader Breader;
	String line;
	ArrayList<String[]> charArray;
	
	public ArrayList<String[]> tileMapArray(String name){
		ArrayList<String[]> charArray = new ArrayList<String[]>();
		try {
			inStream = this.getClass().getClassLoader().getResourceAsStream(name);
			reader = new InputStreamReader(inStream);
			Breader = new BufferedReader(reader);
			
			
			while((line = Breader.readLine()) != null){
				charArray.add(line.split(" "));
			}
			Breader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charArray;
	}
	
	
}
