package Service;

public class LogServiceFactory {
	
	
	private static LogService logservice;
	
	public static LogService getInstance() {
		if(logservice == null)
			logservice = new LogService();
		return logservice;
	}
	
	
}
