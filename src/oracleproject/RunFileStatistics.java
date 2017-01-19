package oracleproject;

import java.io.FileNotFoundException;

/**
 * RunFileStatistic is a class that allows you to test FileStatistics and all its methods. 
 * It takes an array of strings as its arguments. The first string is the filename.txt and
 * the second is a number that corresponds to the method you'd like to test from the 
 * FileStatistic class. The options are as follows: 
 * 1 - Word Count
 * 2 - Line Count
 * 3 - Average number of letters per word
 * 4 - Most commonly occurring letter in the file
 * 
 * Examples : java RunFileStatistics tests/wordCountTest.txt 1
 * 			: java RunFileStatistics tests/lowerCaseTest.txt 3
 */

public class RunFileStatistics {

	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		if ( args.length == 2 ){
			String filePath = args[0];
			FileStatistic fs = new FileStatistic(filePath);
			
		    switch(args[1]) {
			    
			    case "1" :	System.out.println("Word count = " + fs.wordCount());
			    			break;
			    case "2" :	System.out.println("Line count = " + fs.lineCount());
			    			break;
			    case "3" :	System.out.println("Average letters per word = " + fs.averageCharacterCount());
			    			break;
			    case "4" :	System.out.println("Most common letter is " + fs.mostCommonLetter());
			    			break;
			    default  :	System.out.println("Not a valid option. Please check documentation for more info.");
			    			break;
		    }
		} else{
			System.out.println("Please specify valid arguments in the form: \nfilePath/filename.txt optionNo \nCheck readme.txt for more details.");
			System.exit(0);
		}
		
	}
}