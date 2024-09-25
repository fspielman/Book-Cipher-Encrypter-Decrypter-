package gui;

import functionalities.Decrypter;
import functionalities.Encrypter;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Font;

public class BookCipher extends JFrame {
	
	private int lineCnt;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputBookFile;
	private JTextField encryptInput;
	private JTextField decryptInput;
	private JTextField encryptOutput;
	private JTextField decryptOutput;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCipher frame = new BookCipher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookCipher() {
		final  ArrayList<String> bookText = new ArrayList<String>(); // ArrayList where file is inputed in
		
		setBackground(new Color(240, 240, 240));
		setForeground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookCipher.class.getResource("/resources/BookIcon.png")));
		
		setTitle("Book Cipher Encrypter / Decrypter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int x = (int)ge.getCenterPoint().getX();
		int y = (int)ge.getCenterPoint().getY();
		setBounds(x - 627 / 2, y - 261 / 2, 627, 261);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*************************** ALERTS BOX ***************************/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 25, 260, 79);
		contentPane.add(scrollPane);
		
		JTextArea AlertsBox = new JTextArea();
		AlertsBox.setWrapStyleWord(true);
		AlertsBox.setLineWrap(true);
		scrollPane.setViewportView(AlertsBox);
		AlertsBox.setFont(new Font("Monospaced", Font.BOLD, 12));
		AlertsBox.setBackground(new Color(255, 255, 255));
		AlertsBox.setEnabled(false);
		AlertsBox.setEditable(false);
		
		/*************************** BOOK FILE ***************************/
		
		inputBookFile = new JTextField();
		inputBookFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		inputBookFile.setBounds(10, 49, 220, 20);
		inputBookFile.setHorizontalAlignment(SwingConstants.CENTER);
		//inputBookFile.setText("book.txt"); for testing
		contentPane.add(inputBookFile);
		inputBookFile.setColumns(10);
		
		JButton loadBookFile = new JButton("Enter");
		loadBookFile.setFont(new Font("Tahoma", Font.BOLD, 16));
		loadBookFile.setBounds(240, 38, 89, 29);
		loadBookFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File book = new File(inputBookFile.getText());

					// reading inputed book file					
					Scanner bookFileScnr = new Scanner(book);
		            String line;
		            lineCnt = 0;
		            
		            // adding each line of book file into bookText ArrayList 
		            while(bookFileScnr.hasNextLine()) {
		            	line = bookFileScnr.nextLine();
		            	lineCnt++;
		                bookText.add(line);
		            }

		            bookFileScnr.close();	
		        } 
				catch (FileNotFoundException notfound) {
					AlertsBox.append("Cannot find the book file that was inputted\n");
			      }
				catch (Exception exception){
					exception.printStackTrace(); 
				}
			}
		});
		contentPane.add(loadBookFile);

		/*************************** ENCRYPTION ***************************/

		encryptInput = new JTextField();
		encryptInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		encryptInput.setBounds(257, 131, 220, 20);
		//encryptInput.setText("ToEncrypt.txt"); for testing
		encryptInput.setHorizontalAlignment(SwingConstants.CENTER);
		encryptInput.setColumns(10);
		contentPane.add(encryptInput);
		
		encryptOutput = new JTextField();
		encryptOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		encryptOutput.setBounds(10, 131, 220, 20);
		encryptOutput.setHorizontalAlignment(SwingConstants.CENTER);
		//encryptOutput.setText("EncryptOutput.txt"); for testing
		contentPane.add(encryptOutput);
		encryptOutput.setColumns(10);
		
		JButton encryptFileBtn = new JButton("Encrypt");
		encryptFileBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		encryptFileBtn.setBounds(497, 120, 89, 36);
		encryptFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File encryptInFile = new File(encryptInput.getText());
					File encryptOutFile = new File(encryptOutput.getText());
					
					if(bookText.isEmpty()) {
						throw new FileNotFoundException("Book File not inputted");
					}
					
					// throwing FileNotFound Exception if input and output not found
					if(!encryptInFile.exists() && !encryptOutFile.exists()) {
						throw new FileNotFoundException("Encryption Input and Output files not found");
					}
					
					// throwing FileNotFound Exception if input not found
					else if (!encryptInFile.exists()) {
		                throw new FileNotFoundException("Encryption Input file not found");
		            }
					
					// throwing FileNotFound Exception if output not found
					else if (!encryptOutFile.exists()) {
		                throw new FileNotFoundException("Encryption Output file not found");
		            }
					
					// if both file are found -> encrypt 
					if(encryptInFile.exists() && encryptOutFile.exists()) {
						Encrypter E = new Encrypter(); 
						E.Encrypt(encryptInFile, bookText, lineCnt, encryptOutFile, AlertsBox);
					}
					
				}
				// for FileNotFound Exception: prints message in Exceptions box
				catch (FileNotFoundException notfounds) {
			         AlertsBox.append("Error: " + notfounds.getMessage() + "\n");
			    }
				catch(Exception g) {
					AlertsBox.setText("Error: " + g.getMessage() + "\n");
					g.printStackTrace();
				}
			}
		});
		contentPane.add(encryptFileBtn);
		
		/*************************** DECRYPTION ***************************/
		
		decryptInput = new JTextField();
		decryptInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		decryptInput.setBounds(257, 186, 220, 20);
		//decryptInput.setText("ToDecrypt.txt"); for testing
		decryptInput.setHorizontalAlignment(SwingConstants.CENTER);
		decryptInput.setColumns(10);
		contentPane.add(decryptInput);
		
		decryptOutput = new JTextField();
		decryptOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		decryptOutput.setBounds(10, 186, 220, 20);
		decryptOutput.setHorizontalAlignment(SwingConstants.CENTER);
		//decryptOutput.setText("DecryptOutput.txt"); for testing
		decryptOutput.setColumns(10);
		contentPane.add(decryptOutput);
		
		JButton decryptBtn = new JButton("Decrypt");
		decryptBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		decryptBtn.setBounds(497, 175, 89, 37);
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File decryptInFile = new File(decryptInput.getText());
					File decryptOutFile = new File(decryptOutput.getText());
					
					if(bookText.isEmpty()) {
						throw new FileNotFoundException("Book File not inputted");
					}
					
					// throwing FileNotFound Exception if input and output not found
					if(!decryptInFile.exists() && !decryptOutFile.exists()) {
						throw new FileNotFoundException("Decryption Input and Output files not found");
					}
					
					// throwing FileNotFound Exception if input not found
					else if (!decryptInFile.exists()) {
		                throw new FileNotFoundException("Decryption Input file not found");
		            }
					
					// throwing FileNotFound Exception if output not found
					else if (!decryptOutFile.exists()) {
		                throw new FileNotFoundException("Decryption Output file not found");
		            }
					
					// if both file are found -> decrypt 
					if(decryptInFile.exists() && decryptOutFile.exists()) {
						Decrypter Decrypter = new Decrypter(); 
	                    Decrypter.Decrypt(decryptInFile, bookText, decryptOutFile, AlertsBox);
					}
					
				}
				// for FileNotFound Exception: prints message in Exceptions box
				catch (FileNotFoundException notfounds) {
			         AlertsBox.append("Error: " + notfounds.getMessage() + "\n");
			    }
				catch(Exception g) {
					AlertsBox.setText("Error: " + g.getMessage() + "\n");
					g.printStackTrace();
				}
			}
		});
		contentPane.add(decryptBtn);
		
		/*********************************** LABELS ***********************************/

		JLabel encryptInput = new JLabel("Input File to Encrypt");
		encryptInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		encryptInput.setBounds(257, 114, 220, 14);
		encryptInput.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(encryptInput);
		
		JLabel decryptInput = new JLabel("Input File to Decrypt");
		decryptInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		decryptInput.setBounds(257, 168, 220, 14);
		decryptInput.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(decryptInput);
		
		JLabel bookfileInput = new JLabel("Input Book File Name Here");
		bookfileInput.setFont(new Font("Tahoma", Font.BOLD, 16));
		bookfileInput.setBounds(10, 31, 220, 14);
		bookfileInput.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(bookfileInput);
		
		JLabel encryptOutputLabel = new JLabel("Output File for Encryption");
		encryptOutputLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		encryptOutputLabel.setBounds(10, 114, 220, 14);
		encryptOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(encryptOutputLabel);
		
		JLabel dencryptOutputLabel = new JLabel("Output File for Decryption");
		dencryptOutputLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		dencryptOutputLabel.setBounds(10, 168, 220, 14);
		dencryptOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(dencryptOutputLabel);
		
		JLabel AlertsLabel = new JLabel("Alerts:");
		AlertsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		AlertsLabel.setBounds(341, 8, 260, 14);
		AlertsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(AlertsLabel);
	
	}
}
