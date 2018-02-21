package anotherTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PalindromSearch {

	public static void main(String[] args) throws IOException {
		FileInputStream fin;

		String fileStr = "";
			fin = new FileInputStream("input.txt");
			int v = -1;
			while ((v = fin.read()) != -1) {
				fileStr += (char) v;
			}
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
	        if(isPolindrom(fileStr))
	        	writer.println("It is a palindrome");
	        else 
	        	writer.println("It is not a palindrome");
	    
			writer.close();
	}
	
	private static boolean isPolindrom(String str){
		 str = str.toLowerCase();
	        str = str.replace(" ", "");
	        int strLen = str.length();
	        if(strLen == 1)
	        	return true;
	        for(int i = 0; i < strLen / 2; i++)
	            if(str.charAt(i) != str.charAt(strLen - i - 1))
	                return false;
	        return true;

	}
}
