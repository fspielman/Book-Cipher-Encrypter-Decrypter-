/*
 * Decrypter class:
 * decrypts input in the format of line " " word " " character index " " into a single character at that index in the book file
 */

package functionalities;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

public class Decrypter {
	public void Decrypt(File decodeInput, ArrayList<String> bookText, File encryptOutFile, JTextArea AlertsBox) throws Exception{
		int line;
		int word;
		int charInWord;
        PrintWriter printDecrypt = null;
		
        boolean successful = false; // flag to see if beginning of input format is correct -> wrong input
        
		try {
            String input = new String(Files.readAllBytes(decodeInput.toPath())).trim();
            // System.out.println("INPUT: " + input); testing
            
            printDecrypt = new PrintWriter(encryptOutFile);
			/* Splitting inputed numbers into groups of line number, word number in that line, and character number in that word 
			 * (each seperated by a space)
			 * last group for double space, space, or end of message*/
			Pattern p = Pattern.compile("(\\d+)\\s(\\d+)\\s(\\d+)(\\n|\\s\\n|\\s\\s|\\s|$)");
			Matcher m = p.matcher(input);
			
			boolean pattern = false; // flag for pattern to check if beginning input is incorrect 
			// if pattern is wrong after correct input it is skipped due to regex -> (need to make sure input is correct or else wrong message outputted)
			
			while(m.find()) {
				pattern = true; // if it matches pattern is true
				// if matches set group 1 to line, 2 to word, and 3 to character index in word
				line = Integer.parseInt(m.group(1));
				word = Integer.parseInt(m.group(2));
				charInWord = Integer.parseInt(m.group(3));
				// System.out.println("LINE NUM: " + line + " | WORD NUM: " + word + " | CHAR INDEX: " + charInWord);  testing
				
				// inputting all the indexes to retrieve character
				String bookLine = bookText.get(line);
				String[] words = bookLine.split(" ");
				
				String chosenWord = words[word];
				
				char character = chosenWord.charAt(charInWord);
				printDecrypt.print(character); // print character into file to decrypt
				
				String space = m.group(4); // last group either space / 2 spaces / new line
				
				// to print single space in output file if 4th group has two spaces
                if (space.equals("  ")) {
                    printDecrypt.print(" ");
                }
                // to print new line in output file if 4th group has new line
                if (space.equals(System.lineSeparator())) {    
                    printDecrypt.println();
                }
			}
			
			// flag is false means that pattern didn't initially match -> wrong beginning input
			if(!pattern) {
				AlertsBox.append("WRONG INPUT");
			}
			else {
				successful = true; // if at least one pattern matches then it did decrypt something. the rest is user's fault
			}
			
		} 
		catch (Exception exception){
			AlertsBox.setText("Error: " + exception.getMessage());
			exception.printStackTrace();
		}
		// close PrintWriter
		finally {
            if (printDecrypt != null) {
            	printDecrypt.close();
            }
            if(successful) {
            	AlertsBox.append("Successfully Decrypted Message\n"); // print successful if it is true
            }
        }
	}
}
