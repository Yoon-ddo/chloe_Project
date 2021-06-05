package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Service.AdminService;
import Service.AdminServiceFactory;
import Service.LogService;
import Service.LogServiceFactory;
import Service.TransactionService;
import Service.TransactionServiceFactory;
import Service.UserService;
import Service.UserServiceFactory;
import VO.LogVO;
import VO.UserVO;

public abstract class BaseUI implements IBankSysUI{
	
	private Scanner sc;
	protected LogService logservice;
	protected UserService userservice;
	protected TransactionService  transervice;
	protected AdminService adminservice;
	public static LogVO log;
	public static UserVO userlog; // 로그인한사람의 주민번호 
	public static List<UserVO> userloglst;
	
	public BaseUI() {
		sc = new Scanner(System.in);
		logservice = LogServiceFactory.getInstance();
		userservice = UserServiceFactory.getInstance();
		transervice = TransactionServiceFactory.getInstance();
		adminservice = AdminServiceFactory.getInstance();
		log = new LogVO();
		userlog = new UserVO();
		userloglst = new ArrayList<>();
	}
	
	protected String scanStr(String msg) {
		System.out.print(msg);
		String str = sc.nextLine();
		return str;
	}
	
	protected int scanInt(String msg) {
		int num ;			
		try {
			num = Integer.parseInt(scanStr(msg));
		}catch(Exception e) {
			num = -1;
			//System.out.println("유효하지 않은 값입니다. 다시입력하세요.");
		}
		return num;
	}
	
	
	/**
	 * 숫자인지 판별하기
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public boolean isValidNum(String i) throws Exception{
		boolean isNumeric = i.chars().allMatch(Character::isDigit);
		return isNumeric;
	}

}
