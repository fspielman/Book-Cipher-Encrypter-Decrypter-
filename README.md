# Book Cipher Encrypter/Decrypter
Takes a plain text file as input and encrypts it with Book Cipher using another text file. Decrypts encrypted text files. 

## What is a Book Cipher?
A book cipher is a cipher where both the sender and the receiver have access to the same book to use as the key. For the typical book cipher, the sender would send an encoded message in the form of page number, line number, and word number in the given line to represent a specific word. 

I adapted the cipher for
- Line Number
- Word Number
- Character's index within word
to represent a character in the encoded message. This results in a longer more complex encoded message.
// a series of three numbers separated by spaces
// double space for space show example for encoded message eg 12 35 2  2 45 3

## Decoding a Message
To decode a message, the user provides:
- Book File: text file used for indexing
- Input Decryption File: text file containing the encoded message
- Output Decryption File: text file that would contain the decrypted message after pressing

## Steps for Decoding
1. Book file is scanned and each line is added to an ArrayList
2. 
When this button is clicked, the book file is scanned, and each line is added to
an ArrayList to create easier indexing for decryption or encryption. The input decryption file is
where the encoded message is put into, which is .
As previously stated, this would be the line number, word number, and the character’s index in
the word.
Additionally, for the input two spaces would represent a space when decoded, to ensure
proper separation of words and it could make the encrypted message a little more confusing
when looking at it. The output to decryption would be to acquire the original message that was
sent. After the input and output file names are inserted into their corresponding textboxes the
user would then click on the “Decrypt” button.

The core algorithm for the decryption function was using regular expressions to create
the pattern of "(\\d+)\\s(\\d+)\\s(\\d+)(\n+|\\s\\s|\s|$).” Each group besides the last one represents
the line, word, and character of the index with spaces in between to match the formatting of the
input. The last group gives the option for a double space, single, space, or the end of the line to
account for any conditions that could occur. The first three groups are parsed into integers and
then the indexes are inputted into the bookfile to retrieve character and print it out in the output
file. And if the input didn’t match the pattern, then input was not formatted correctly. 

This uses regex for splitting the imputed integers, separating the ArrayList that copied the book file to retrieve the character, and then writing onto the output file.

## Encoding a Message
To encode a message, the user provides:
1. Book FIle: text file used for indexing
2. Input Encryption File: text file containing message to encode
3. Output Encryption File: text file that would contain the encrypted message 

## Steps for Encoding
user would need to fill in the text boxes that corresponded to the output and input files
for encryption instead of decryption. When the encryption button is clicked, the input is read, the
indexes for every letter are identified and stored in a HashMap. After, the indexes are shuffled
and then printed in the output file.

Encryption function works by first splitting the letters of the input and creating an array
to store each line of the book file. A HashMap is instantiated that maps a letter to an ArrayList of
Indexes from my custom class. Then each index is found by searching through each letter of
input, creating an ArrayList of indexes for each letter, going through each line and each word,
searching the word multiple times for the character. The reasoning for searching multiple times is
that if there were several of the same characters in a word, then the .indexOf method would only
retrieve the first instance of that letter. After that, I used the Collections.shuffle method to shuffle all
the indexes for each letter to add more complexity to the encryption by differentiating indexes
when the same letter appears in the input and there are multiple indexes for that letter. To show
this algorithm, let’s say given the input “a”, then each index would get searched for and input it
into the HashMap like so, [“a”, {0 0 0, 0 2 1, 2 3 4}]. Then the printed output for that character
would be one out of the three indexes that were found.



