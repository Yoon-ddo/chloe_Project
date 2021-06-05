package Service;

public class UserServiceFactory {
	private static UserService userservice;
	
	public static UserService getInstance() {
		if(userservice == null)
			userservice = new UserService();
		return userservice;
	}

}
