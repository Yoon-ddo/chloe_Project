package UI;

import VO.UserVO;

public class UserDeleteAcoountUI extends BaseUI{
	
	public BankSysUI ui = new BankSysUI();
	public UserVO currentinfo;
	public UserVO vov;
	
	public UserDeleteAcoountUI() {
		currentinfo = new UserVO();
		vov = new UserVO();
	}

	@Override
	public void execute() throws Exception {
		while(true) {
			String account = scanStr("계좌번호를 입력하세요(숫자만입력하세요) :");
			boolean isNumeric = isValidNum(account);
			if(!isNumeric) {
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
				continue;
			} else {
				vov.setAccount(account);
				currentinfo = userservice.myAllAcc("account", vov);
				if(currentinfo.getAccount()!=null) {
					if(currentinfo.getAccount().equals(account)){
						break;									
					}
				}else {
					System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
					continue;
				}
			}
		}
		
		
		for(int i = 0; i<3; i++) {
			String pw = scanStr("계좌비밀번호를 입력하세요. : ");
			vov.setAccpwd(pw);
			boolean pwchk = userservice.checkPwService("accpwd", vov);
			if(i == 2) {
				System.out.println("3회이상 틀렸습니다. 다시 로그인하세요.");
				ui.execute();
			}else {
				if(!pwchk) {
					System.out.println("비밀번호가 틀렸습니다.");
					continue;
				} else {
					userservice.deleteAccountService(currentinfo);
					break;
				}							
			}
		}
		
	}

}
