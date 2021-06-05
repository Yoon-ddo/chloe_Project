package Service;

public class Sequence {
	
	private static int accountCode = 1;
	public static synchronized int getAcc() {
		return Sequence.accountCode++;
	}

}
