package Service;

public class TransactionServiceFactory {
	
	
	private static TransactionService  transervice;
		
	public static TransactionService getInstance() {
		if(transervice == null)
			transervice = new TransactionService ();
		return transervice;
	}

}
