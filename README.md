# Book Cipher Encrypter/Decrypter
Takes a plain text file as input and encrypts it with Book Cipher using another text file. Decrypts encrypted text files. 
//talk about GUI and key features (exceptions box, randomizing indexes, double space in encoded to represent single space in encyrpted
//paste pictures 

## What is a Book Cipher?
A book cipher is a cipher where both the sender and the receiver have access to the same book to use as the key. For the typical book cipher, the sender would send an encoded message in the form of page number, line number, and word number in the given line to represent a specific word. 

I adapted the cipher for
- Line Number
- Word Number
- Character's index within word
to represent a character in the encoded message. This results in a longer more complex encoded message.
Each category of the encoded message is seperated by a space and a double space is equivalent to a space in the decrypted message. 
Example: An input of 12 35 2 represents the 2nd character in the 35th word on the 12th line of the book text file

## Decryption
### Decrypt a Message
To decode a message, the user provides:
- Book File: text file used for indexing
- Input Decryption File: text file containing the encoded message
- Output Decryption File: text file that would contain the decrypted message after pressing

### Steps to Decrypt File
- Input Book File in its respective text box and click enter
- Type the Input and Output Decryption Files into their respective text boxes and click enter
- If no errors occured the Output Decryption file contains the decrypted message

### How the Decryption Function Works? 
The core algorithm for the decryption function was using regular expressions to create
the pattern of "(\\d+)\\s(\\d+)\\s(\\d+)(\n+|\\s\\s|\s|$).” Each group besides the last one represents
the line, word, and character of the index with spaces in between to match the formatting of the
input. The last group gives the option for a double space, single, space, or the end of the line to
account for any conditions that could occur. The first three groups are parsed into integers and
then the indexes are inputted into the bookfile to retrieve character and printed out in the output
file. And if the input didn’t match the pattern, then the input was not formatted correctly and an exception is thrown. 

## Encryption
### Encrypt a Message
To encode a message, the user provides:
1. Book FIle: text file used for indexing
2. Input Encryption File: text file containing message to encode
3. Output Encryption File: text file that would contain an encrypted version of the message  

### Steps to Encrypt File
- Input Book File in its respective text box and click enter
- Type the Input and Output Encryption Files into their respective text boxes and click enter
- If no errors occured the Output Encryption file contains the encrypted message

### How the Encryption Function Works?
Encryption function works by first splitting the letters of the input and creating an array
to store each line of the book file. A HashMap is instantiated that maps a letter to an ArrayList of
Indexes from my custom class. Then each index is found by searching through each letter of
input, creating an ArrayList of indexes for each letter, going through each line and each word,
searching the word multiple times for the character. The reasoning for searching multiple times is
that if there were several of the same characters in a word, then the .indexOf method would only
retrieve the first instance of that letter. After that, I used the Collections.shuffle method to shuffle all
the indexes for each letter to add more complexity to the encryption by differentiating indexes
when the same letter appears in the input and there are multiple indexes for that letter.

//remove this?????
To show this algorithm, let’s say given the input “a”, then each index would get searched for and input it
into the HashMap like so, [“a”, {0 0 0, 0 2 1, 2 3 4}]. Then the printed output for that character
would be one out of the three indexes that were found.

## Exceptions Text Box
The program also contains an exceptions text box that displays errors when they occur.  



