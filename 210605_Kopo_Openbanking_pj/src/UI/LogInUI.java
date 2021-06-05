package UI;

import VO.LogVO;

public class LogInUI extends BaseUI{

	public static boolean ckuser;
	public static boolean ckadmin;
	
	protected static String rescd;
	
	
	
	public LogInUI() {
		ckadmin = false;
		ckuser = false;
	}

	@Override
	public void execute() throws Exception {
		String id = scanStr("- ID를 입력하세요. : ");
		String pwd = scanStr("- 비밀번호를 입력하세요 : ");
		
		LogVO logvo = logservice.LogInService("id",id);
		if(logvo.getId() == null) {
			System.out.println("유효하지 않은 정보입니다.");
		}else if(!logvo.getPwd().equals(pwd)) {
			System.out.println("비밀번호가 틀립니다.");
			
		}else if(logvo.getId().equals(id) && logvo.getPwd().equals(pwd)) {
			userlog.setRes_code(logvo.getRes_code());
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			System.out.println("\t\t[ "+logvo.getName()+" ]님 환영합니다!");
			System.out.println();
			System.out.println("-------------------------------------------------------------------");
			log = logvo;
			//System.out.println(log);
			if(logvo.getId().equals("admin")) {
				ckadmin = true;
			}else {
				rescd = logvo.getRes_code();
				BaseUI.log = logservice.LogInService("res_code",rescd); 
				//System.out.println(BaseUI.log);
				ckuser = true;				
			}
		}else {
			System.out.println("로그인에 실패했습니다.");
		}
	
	}
	
	public static boolean ckAdmin() throws Exception{
		return ckadmin;
	}
	
	public static boolean ckUser() throws Exception{
		return ckuser;
	}
	

}
