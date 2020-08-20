
@SuppressWarnings("serial")
public class DecryptionError extends Exception {
	

	public DecryptionError() {
		super("Entered word cannot be decrypted. \nCheck entered values for board size, starting row, and starting column. \nSpecial characters are not supported.");
	}

}
