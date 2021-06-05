package UI;

import VO.UserVO;

public class UserUpdateAccountInfoUI extends BaseUI{
	
	public BankSysUI ui = new BankSysUI();
	public UserVO currentinfo;
	public UserVO vov;
	
	public UserUpdateAccountInfoUI() {
		currentinfo = new UserVO();
		vov = new UserVO();
	}
	
	

	@Override
	public void execute() throws Exception {
		while(true) {
			String account = scanStr("계좌번호를 입력하세요(숫자만입력하세요) :");
			System.out.println();
			boolean isNumeric = isValidNum(account);
			if(!isNumeric) {
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
				continue;
			} else {
				vov.setAccount(account);
				currentinfo = userservice.myAllAcc("account", vov);
				//System.out.println(currentinfo);
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
		showUpdate(currentinfo);
		
	}
	
	public int whatupdate() {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("      [1].별칭\t\t[2].계좌비밀번호");
		System.out.println("-------------------------------------------------------------------");
		int what = scanInt("업데이트 할 항목을 선택하세요 : ");
		System.out.println();
		return what;
	}
	
	/**
	 * 비밀번호/별칭 바꾸기
	 * @param c
	 * @throws Exception
	 */
	public void showUpdate(UserVO c) throws Exception {
		
		while(true) {
			int wht = whatupdate();
			switch(wht) {
			case 1:
				String alias = scanStr("별칭을 입력하세요 : ");
				System.out.println();
				c.setAccalias(alias);
				userservice.updateAccService("accalias",c);
				break;
			case 2:
				for(int i = 0; i<3; i++) {
					String pw = scanStr("계좌비밀번호를 입력하세요 : ");
					System.out.println();
					//UserVO pvo = new UserVO();
					vov.setAccpwd(pw);
					boolean pwchk = userservice.checkPwService("accpwd", vov);
					if(i == 2) {
						System.out.println("비밀번호를 3회이상 틀렸습니다. 다시 로그인하세요.");
						ui.execute();
					}else {
						if(!pwchk) {
							System.out.println("비밀번호가 틀렸습니다.");
							continue;
						} else {
							pw = scanStr("변경할 비밀번호를 입력하세요 : ");
							System.out.println();
							c.setAccpwd(pw);
							userservice.updateAccService("accpwd",c);
							System.out.println("계좌비밀번호가 변경되었습니다.");
							break;
						}
						
					}
					
				}
				break;
			default : 
				System.out.println("유효하지 않은 메뉴입니다.");
			}
			break;
			
		}
	
		
	}

}//c
