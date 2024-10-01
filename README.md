# Book Cipher Encrypter/Decrypter
## Overview
This application allows users to encrypt or decrypt messages using a Book Cipher technique, utilizing JSwing for the GUI. The program takes a plain text file as input and processes it with a reference text file to produce an encrypted or decrypted output into another file.

### Prerequisites
To run the project, [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) must be installed.

### GUI Layout for Book Cipher:

![Book Cipher GUI](https://github.com/user-attachments/assets/afb4e720-ddd7-403f-98a2-aba4862698b9)

## What is a Book Cipher?
A book cipher is a cipher where both the sender and the receiver have access to the same book to use as the key. For the typical book cipher, the sender would send an encoded message in the form of a page number, line number, and word number in the given line to represent a specific word. 

### Adaptation of the Book Cipher
The cipher was adapted to represent a character through the:
- Line Number
- Word Number
- Character's index within the word
This results in a more complex encoded message. Each category of the encoded message is separated by a space and a double space representing a space in the decrypted message. 

#### Example: An input of 12 35 2 represents the 2nd character in the 35th word on the 12th line of the book text file.

## Encrypting / Decrypting A File
### User Input
To Encrypt or Decrypt a Message, the User Provides:
- Book File: The text file used for indexing.
- Input File: The text file containing the message to encode (for encryption) or the encoded message (for decryption).
- Output File: The text file that will contain the encrypted version of the message (for encryption) or the decrypted message (for decryption).

### User Guide
1. Input the Book File in its respective text box and press enter.'
2. Enter the Input and Output Files into their respective text boxes and press enter.
3. If no errors occur, the Output File will contain the processed message (encrypted or decrypted).

### How the Decryption Function Works
- The encryped message is parsed using regular expressions (group for the line, word, letter, and spacing or new line).
- Retrieves character from the book file using the first three groups from the regex
- If the input didn’t match the pattern, then the input was not formatted correctly and an exception is thrown. 

#### Example of Successful Decryption:

![Successful Decryption](https://github.com/user-attachments/assets/4887d0cb-537f-4f6e-88ae-e08c7c609813)

### How the Encryption Function Works
- The input message is split into an array of characters for each line in the message
- The letter is searched in the book file and inputted into a HashMap to store each index of that letter
- The Collections.shuffle method shuffles all of the indexes for each letter, differentiating indexes when the same letter appears multiple times in the input.

#### Example of Successful Encryption:

![Successful Encryption](https://github.com/user-attachments/assets/81bcacf8-b294-4be5-8b77-f38232a1af74)

## Exceptions Text Box
The program also contains an exceptions text box that displays errors when they occur.  
### Instances for Exceptions:
1. Wrong Files Inputted
   
![Wrong Encryption Files (same functionality for Decryption)](https://github.com/user-attachments/assets/cc2e00fb-34ba-4e0e-aa96-7306ceb6732d)

2. Wrong Decryption Input

![Wrong Decrypt Input](https://github.com/user-attachments/assets/59a8b36c-1926-430f-87e6-7e3e0d4f0318)

3. Wrong Book FIle Inputed, which makes it unable to encrypt or decrypt
   
![wrong book file inputted, Can't Encrypt or Decrypt](https://github.com/user-attachments/assets/2848ae5f-e2d7-49a1-b0b8-a3f99ada7b6e)

4. Characters in the Message are not in the Book File
   
![Input not in bookfile, only encrypts correct input, successful encryption doesn't show up](https://github.com/user-attachments/assets/5d89b914-2272-454f-8fbd-a5268dc9a048)

