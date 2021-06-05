package Service;

public class AdminServiceFactory {
	private static AdminService adminservice;
	
	public static AdminService getInstance() {
		if(adminservice == null)
			adminservice = new AdminService();
		return adminservice;
	}
}
