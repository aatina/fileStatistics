package oracleproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FileStatistics is a library of methods that return data of a .txt file. The constructor 
 * takes in a filePath from the current working directory as a parameter.
 * A buffered reader is used to store and read a line of the file at a time.
 * lineCount and wordCount are private class members as they are used multiple times throughout 
 * the class. 
 * The four main methods are lineCount, wordCount, averageCharacterCount and mostCommonLetter. 
 * The readFile method is called at the start of each of the above four. It resets the buffered
 * reader back to the start of the file.
 * 
 * @author	Aatina Punjabi
 * @version 1.0
 * 
 */

public class FileStatistic {
	
	private BufferedReader br;
	private String filePath;
	
	/**
	 * The constructor takes in the file path relative to the working directory.
	 * 
	 * @param filePath The file path of the txt file being checked
	 * 
	 */ 
	public FileStatistic(String filePath){
		try {
			validateFileExtension(filePath);
		} catch (FileFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ValidateFileExtension is a boolean that takes a filePath and checks if it's a .txt file. 
	 * It does this by simply checking the last 3 characters of the filePath. 
	 * If the filePath is invalid then an exception is thrown.
	 * 
	 * @param filePath
	 * @throws FileFormatException
	 *
	 */
	private void validateFileExtension(String filePath) throws FileFormatException{
		
		if (filePath.length() >= 3){
			String fileCheck = filePath.substring(filePath.length() - 3);
			if( "txt".equals(fileCheck) ){
				this.filePath = filePath;
			} else {
				throw new FileFormatException("Invalid File extension");
			}
		} else {
			throw new FileFormatException("Invalid File extension");
		}
	}
	
	/**
	 * 
	 * ReadFile is called at the start of every method to reset the buffered reader to the start of the file
	 * 
	 * @return True if br is a valid filepath
	 */
	
	private boolean readFile(){
		try {
			br = new BufferedReader(new FileReader(this.filePath)); 
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	/**
	 * 
	 * LineCount returns the number of lines in a file as an primitive integer type. 
	 * Returns -1 if file was not found.
	 * 
	 * @return Number of lines in current file
	 * 
	 */
	
	public int lineCount(){
		int lineCount = 0;
		
		/* If readFile is false then the file was not found */
	    if ( readFile() ){
			try {
				
				/* Loops till the end of file */
				while (br.readLine() != null) {
				    lineCount++;
				}
			} catch (IOException e) {
				return -1;
			}
			return lineCount;
	    } 
	    /* File was not found */
	    else return -1; 
	}
	
	/**
	 * 
	 * WordCount returns the number of words in a file as an primitive integer  type. 
	 * Returns -1 if file was not found. 
	 * 
	 * @return Number of words in current file
	 * 
	 */
	
	public int wordCount(){
		int wordCount = 0;
		
		/* If readFile is false then the file was not found */
		if( readFile() ){
			try {
				String line = br.readLine();
				while (line != null) {
					/* Each line is split by a regex for any whitespace */
					wordCount += line.split("\\s").length;
					line = br.readLine();
				}
			} catch (IOException e) {
				return -1;
			}
			return wordCount;
		} /* File was not found */
		else return -1; 
	}
	
	/** 
	 * 
	 * AverageCharacterCount returns the average number of characters per word. 
	 * Returns -1 if file was not found. LineCount and WordCount are called at the beginning; 
	 * their values are required for a calculation in this method 
	 * 
	 * @return Average number of letters per word in the file
	 * 
	 */
	
	public float averageCharacterCount(){
		int lineCount = lineCount(); 
		int wordCount = wordCount();
		
		/* If readFile is false then the file was not found */
		if( readFile() ){
			float averageCharCount = 0;
			int charCount = 0;
			String line;
			String[] words;
			String word;
			try {
				for(int j = 0; j < lineCount; j++){
					/* Get all the words on the current line and store in an array */
					line = br.readLine();
					words = line.split(" ");
				    for(int i = 0; i < words.length; i++){
						/* Loop through each letter in the word */
				    	word = words[i];
				    	charCount += word.length();
				    }
				}
			    averageCharCount = (float) (Math.round(((float)charCount/wordCount) * 10.0) / 10.0);
			} catch (IOException e) {
				return -1;
			}
			return averageCharCount;
		} /* File was not found */
		else return -1;
	}
	
	/** 
	 * 
	 * MostCommonLetter returns the most recurring letter in the file as a map with letter = #occurrences. 
	 * If there are multiple characters with the same value, then it returns the one with the lowest index
	 * Returns null if file is not found 
	 * LineCount is called at the beginning; its value is required for a calculation in this method 
	 * @return The first found most occurring letter in the file
	 * 
	 */
	
	public Map.Entry<Character , Integer> mostCommonLetter(){
		Map<Character, Integer> alphabet = new HashMap<Character, Integer>();
		String line;
		char letter = '\0';
		Map.Entry<Character, Integer> maxEntry = null;
		
		int lineCount = lineCount();
		/* If readFile is false then the file was not found */
		if( readFile() ){
			try {
				for(int j = 0; j < lineCount; j++){
					/* Loop through every line in file and make all characters lower case. */
					line = br.readLine();
					line = line.toLowerCase();
					/* Loop through each word in the line.
					 * For each unseen letter in the word store it in alphabet, incrementing is value each time it's encountered. 
					 */
					for (int i = 0; i < line.length(); i++){
						letter = line.charAt(i);
						if( letter == ' ' ){
							continue;
						} if (alphabet.containsKey(letter)) {
							alphabet.put(letter, alphabet.get(letter) + 1);
						} else {
							alphabet.put(letter, 1);
						}
				    }
				}
				for (Map.Entry<Character, Integer> entry : alphabet.entrySet()) {
				    if ( maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0 )
				    { 
				    	/* Largest value in alphabet */
				        maxEntry = entry;				
				    }
				}
			} catch (IOException e) {
				return null;
			}
			return maxEntry;
		} /* File was not found */
		else return null;
	}
}

