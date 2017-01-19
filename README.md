# fileStatistic
#####To test the library FileStatistics you have to run the class RunFileStatistics, using the command prompt or a suitable IDE like Eclispe. 

###To run the using command prompt: 

Set working directory to /aatina_project/src/

Compile using javac oracleproject/RunFileStatistics.java

Run the file with two parameters: the path of the file from the current working directory and the method you'd like to run.

Eg :	java oracleproject/RunFileStatistics tests/test1.txt 1 - This would run FileStatistics.wordCount()
	java oracleproject/RunFileStatistics tests/test0.txt 2 - This would run FileStatistics.lineCount()

	- The methods are as follows: 
		 1 - Word Count
 		 2 - Line Count
 		 3 - Average number of letters per word
	 	 4 - Most commonly occurring letter in the file

  Some notes:
  - The methods return a null value (or -1 for int methods), if the file is not valid.
  - The class works for special characters and different alphabets (eg japanTest), but the method mostCommonLetter may not print out the character correctly. This is simply a output issue due to the nature of some languages.
  - FileFormatException is an IO exception class that for some reason would not import, so it’s put within the package.

Check the javadocs - /doc/index.html for more details about the class and its methods.

Aatina Punjabi
19/01/2017
