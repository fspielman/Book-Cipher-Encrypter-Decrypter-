/*
 * Encryption class:
 * Encrypts input file in format of message and prints out the indexes of each character from book file
 */

package functionalities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JTextArea;

public class Encrypter { 
	
	/*
	 * Indexes class:
	 * used for assigning and retrieving the elements of the index
	 */
	
	public class Indexes{
		int line;
	    int word;
	    int charIndex;

	    // constructor that assigns each element of indexes
	    public Indexes(int line, int word, int charIndex) {
	        this.line = line;
	        this.word = word;
	        this.charIndex = charIndex;
	    }
	    
	    // for testing -> for outputting each element in Indexes (was getting memory address before)
        public String toString() {
            return "{line:" + line + ", word:" + word + ", charIndex:" + charIndex + "}";
        }
        
        // getters to retrieve index values
        public int getLine() {return line;}
        
        public int getWord() {return word;}

        public int getCharIndex() {return charIndex;} 
	}
	
	public void Encrypt(File encodeInput, ArrayList<String> bookText, int lineCnt, File encodeOutput, JTextArea AlertsBox) throws FileNotFoundException {
		PrintWriter printEncrypt = new PrintWriter(encodeOutput);
		boolean successful = true;
		
		try {
            String input = new String(Files.readAllBytes(encodeInput.toPath())).trim();
			
            // inputting each letter from input into an array
			String[] letters = (input).split("");
			
			/*for(int i=0; i<letters.length; i++) {
				System.out.print(letters[i] + "");
			}
			System.out.println(); 		testing input*/
			
			// creating an array inLine to contain each line of the booktext
			String[] inLine = new String[bookText.size()];
			for(int i=0; i<bookText.size(); i++) {
				inLine[i] = bookText.get(i);
			}
			
			// instantiating hash map that would eventually contain the letter and all of its indexes from the booktext
			HashMap<String, ArrayList<Indexes>> forLetter = new HashMap<>();
						
			// finding index for inputed letter
			for(String letter : letters) {
				ArrayList<Indexes> indexes = new ArrayList<>();
				
				for(int j=0; j<lineCnt; j++) {
					String[] words = inLine[j].split(" ");
					
					/* if letter is contained in word, set charIndex to the value of i, add it as another index 
					 * and search word in case of same character in the same word*/
					for(int k=0; k<words.length; k++) { 
						if(words[k].contains(letter)) {
							for(int i=0; i< words[k].length(); i++) {
								if(words[k].charAt(i) == letter.charAt(0)) {
									int charIndex = i;
									indexes.add(new Indexes(j,k,charIndex));
								}
							}
							
						}										
					}
					
				}
				// putting array of indexes with its associated letter in HashMap
				forLetter.put(letter, indexes);
			}
			
			/*printing it out for testing (what String toString() was used for)
			for (String letter : forLetter.keySet()) {
                System.out.println("Letter: " + letter + " -> Indexes: " + forLetter.get(letter).toString());
            }	for testing */
						
			// flag for if letter is at the beginning of new line 
			boolean letterAtNewLine = true;
			
			for(String letter : letters) {
				ArrayList<Indexes> indexes = forLetter.get(letter);
				
				//shuffle the indexes for current letter (randomizes if same letters used in message) - based on letter frequency in book file
                Collections.shuffle(indexes);    
                
				//System.out.println("LETTER: " + letter + " FREQUENCY:" + indexes.size()); testing if all letters are accounted for
                
                // print indexes into output if indexes are found
				if(!indexes.isEmpty() && indexes != null) {
					Indexes chosenIndex = indexes.get(0);
					// if first letter of new line
					if(letterAtNewLine) {
						letterAtNewLine = false; // set boolean to false after it is true
						printEncrypt.print(chosenIndex.getLine() + " " + chosenIndex.getWord() + " " + chosenIndex.getCharIndex());
					}
					else {
						printEncrypt.print(" " + chosenIndex.getLine() + " " + chosenIndex.getWord() + " " + chosenIndex.getCharIndex());
					}
				}
				else {
					// add space after indexes if space in between input letters
					if(letter.equals(" ")) {
						printEncrypt.print(" ");
					}
					
					// if new line
					else if(letter.equals(System.lineSeparator()) || letter.equals("\r") || letter.equals("\n") || letter.equals("\r\n")) {
						if (letter.equals("\n") || letter.equals(System.lineSeparator())) {
			                printEncrypt.println();
			                letterAtNewLine = true; // set it to true since next character will be at the beginning of the next line
			            }
					}
					
					// if index is not found
					else{
						AlertsBox.append("INDEX NOT FOUND FOR \"" + letter + "\" IN THE BOOKFILE\n");
						successful = false; // index not found -> not successful
					}
				}
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
            if (printEncrypt != null) {
            	printEncrypt.close();
            }
            if(successful) {
            	AlertsBox.append("Successfully Encrypted Message\n"); // print successful if it is true
            }
		}
	}
}





