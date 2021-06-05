package Service;

import DAO.LogDAODB;
import VO.LogVO;

public class LogService {
	
	private LogDAODB dao;
	
	public LogService() {
		dao = new LogDAODB();
	}

	
	public LogVO LogInService(String key, String val) throws Exception{
		LogVO logvo = dao.GetLogInfo(key,val);
		return logvo;
	}
	
	public void SignInputService(LogVO newlog) throws Exception{
		dao.SignInput(newlog);
	}



}
