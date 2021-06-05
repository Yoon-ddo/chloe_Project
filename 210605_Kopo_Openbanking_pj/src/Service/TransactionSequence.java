package Service;

public class TransactionSequence {
	
	private static int TransactionCode = 1;
	
	public static synchronized int getTranscd() {
		return TransactionSequence.TransactionCode++;
	}
}

