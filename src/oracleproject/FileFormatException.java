package oracleproject;

/*
 * Credit - https://www.d.umn.edu/~gshute/cs5741/javadoc/file/FileFormatException.html
 */

import java.io.IOException;

public class FileFormatException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileFormatException(String string) {
		super(string);
	}
}

