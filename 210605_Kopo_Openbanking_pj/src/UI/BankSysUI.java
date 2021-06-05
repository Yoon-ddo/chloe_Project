package UI;

public class BankSysUI extends BaseUI{
	
	public static boolean ckadmin;
	public static boolean ckuser;
	
	@Override
	public void execute() throws Exception {
		
		while(true) {
			try {
				String t = menu();
				boolean isNumeric = isValidNum(t);
				if(isNumeric) {
					int type = Integer.parseInt(t);
					switch(type) {
					case 1: // 로그인
						LogInUI liui = new LogInUI();	
						liui.execute();
						ckadmin = liui.ckAdmin();
						ckuser = liui.ckUser();
								
						if(ckadmin == true) {
							AdminUI aui= new AdminUI();
							aui.execute();
						}else if(ckuser == true){
							UserUI uui = new UserUI();
							uui.execute();
						}
						
						break;
					case 2://회원가입
						SignInUI siui = new SignInUI();
						siui.execute();
						break;
					case 0://종료
						ExitUI exui = new ExitUI();
						exui.execute();
						break;
					default : System.out.println("유효하지 않은 메뉴입니다.");
					}
				} else System.out.println("유효하지 않은 메뉴입니다.");
				
			} catch (Exception e) {
				System.out.println("유효하지 않은 메뉴입니다.");
				e.printStackTrace();
			}
		}
		
		
	}
	
	public String menu() throws Exception{
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		System.out.println("\t\t하나원큐 통합계좌 관리 시스템에 오신 것을 환영합니다.");
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\t     [1]. 로그인     [2]. 회원가입     [0]. 종료");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		String type = scanStr("원하는 메뉴를 선택하세요. : ");
		System.out.println();
		return type;
		
	}
	

}//c
