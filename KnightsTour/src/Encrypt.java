import java.util.*;
import javax.swing.*;

public class Encrypt {
	protected static KnightsTour tour;

	public void run() {
		Object[] options = { "Encrypt", "Decrypt" };
		if (JOptionPane.showOptionDialog(null, "Encrypt or Decrypt?", "Welcome to the Knight's Tour Encryptor.",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null) < 1) {
			if (makeTour(8)) {
				String encryption = encrypt(JOptionPane.showInputDialog("Enter the word to be encrypted."));
				JOptionPane.showMessageDialog(null, "Encrypted word: " + encryption);
				
					JOptionPane.showMessageDialog(null, "\nStarting row: " + tour.findSpaceWithMove(0).getRow()
							+ "\nStarting column: " + tour.findSpaceWithMove(0).getCol());
					System.out.println("\nStarting row: " + tour.findSpaceWithMove(0).getRow() + "\nStarting column: "
							+ tour.findSpaceWithMove(0).getCol() + "\nEncrypted word: " + encryption);
				
			}
		} else {
			if (makeTour(8, Integer.parseInt(JOptionPane.showInputDialog("Enter the starting row.")),
					Integer.parseInt(JOptionPane.showInputDialog("Enter the starting column.")))) {
				String decrypted = decrypt(JOptionPane.showInputDialog("Enter the word to be decrypted."));

				if (decrypted.equals(""))
					return;
				else {
					JOptionPane.showMessageDialog(null, "Decrypted word: " + decrypted);
				}
			}
		}
	}

	public static boolean makeTour(int size, int row, int col) {
		if (size % 8 != 0 || size == 0) {
			JOptionPane.showMessageDialog(null, "The size must be divisible by 8.");
			return false;
		} else if (row < 0 || col < 0 || row >= size || col >= size) {
			JOptionPane.showMessageDialog(null, "The row and column entered are out of bounds.");
			return false;
		} else {
			tour = new KnightsTour(size, row, col);
			tour.solve();
			return true;
		}
	}

	public static boolean makeTour(int size) {
		if (size % 8 != 0 || size == 0) {
			System.out.println("The size must be divisible by 8.");
			return false;
		} else {
			int row = (int) (Math.random() * size);
			int col = (int) (Math.random() * size);
			tour = new KnightsTour(size, row, col);
			tour.solve();
			return true;
		}
	}

	public static String encrypt(String word) {
		String encrypted = "";
		String characters = "ihap$.{wg}@o-4`~ne8/]6mxk#9f3cr,=ds!)>|t_2yvjq1^5[0?l7b(%u*&z<+:";
		String alphabet = "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		HashMap<Space, String> crypto = new HashMap<Space, String>();
		int c = 1;
		for (int row = 0; row < tour.getBoard().length; row++) {
			for (int col = 0; col < tour.getBoard().length; col++) {
				crypto.put(tour.getBoard()[row][col], String.valueOf(characters.charAt(c - 1)));
				c++;
			}
		}
		for (int letter = 0; letter < word.length(); letter++) {
			encrypted += crypto.get(tour.findSpaceWithMove(alphabet.indexOf(word.charAt(letter))));
		}

		return encrypted;
	}

	public static String decrypt(String word) {
		String decrypted = "";
		String characters = "ihap$.{wg}@o-4`~ne8/]6mxk#9f3cr,=ds!)>|t_2yvjq1^5[0?l7b(%u*&z<+:";
		String alphabet = "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		tour.showBoard();
		HashMap<String, Space> decrypto = new HashMap<String, Space>();
		int c = 1;
		for (int row = 0; row < tour.getBoard().length; row++) {
			for (int col = 0; col < tour.getBoard().length; col++) {
				decrypto.put(String.valueOf(characters.charAt(c - 1)), tour.getBoard()[row][col]);
				c++;
			}
		}
		for (int letter = 0; letter < word.length(); letter++) {
			try {
				decrypted += alphabet.charAt(decrypto.get(String.valueOf(word.charAt(letter))).getMoveNum());
			} catch (StringIndexOutOfBoundsException e) {
				try {
					throw new DecryptionError();
				} catch (DecryptionError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return "";
				}
			}
		}

		return decrypted;
	}

}
