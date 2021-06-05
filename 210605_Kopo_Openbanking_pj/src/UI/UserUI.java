package UI;

import VO.UserVO;

public class UserUI extends BaseUI{
	
	
	public static UserVO currentinfo;
	
	public UserVO vov = new UserVO();
	public String account;
	
	
	public UserUI() {
		currentinfo = new UserVO();
	}

	@Override
	public void execute() throws Exception {
		String res = LogInUI.rescd;
		while(true) {
			BaseUI ui = null;
			userloglst = userservice.showAllAccList(res);
			if(userloglst.size()==0) {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("\t\t정보가 없습니다. 영업점에 문의하세요.");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("\t\t\t[0]. 로그아웃");
				System.out.println("-------------------------------------------------------------------");
				String exit = scanStr("원하는 메뉴를 선택하세요. : ");
				System.out.println();
				boolean isN = isValidNum(exit);
				if(!isN) {
					System.out.println("유효하지 않은 메뉴입니다! 다시입력하세요!");
					continue;
				}else {
					int ex = Integer.parseInt(exit);
					if (ex == 0) {
						ui = new LogOutUI();
						ui.execute();				
					}else {
						System.out.println("유효하지 않은 메뉴입니다! 다시입력하세요!");
						continue;
					}
				}

			}else {
				System.out.println();
				System.out.println("\t[ 보유한 계좌 목록 ]");
				System.out.println();
				for(UserVO u : userloglst) {
					System.out.println("-------------------------------------------------------------------");
					System.out.println("\t[은행명] "+userservice.BankName(u.getBank_code()));
					System.out.print("\t[계좌번호] "+userservice.AccPrint(u.getAccount())+"\t");
					System.out.println("   [별칭] "+u.getAccalias());
					System.out.println("\t[잔액] "+u.getBalance());
				}
				System.out.println("-------------------------------------------------------------------");
			}
			try {
				BaseUI uis = null;
				int type = menu();
				switch(type) {
				case 1: // 계좌등록
					uis = new UserMakeAccountUI();
					uis.execute();
					break;
				case 2://계좌수정
					uis = new UserUpdateAccountInfoUI();
					uis.execute();
					break;
				case 3: //계좌 삭제
					uis = new UserDeleteAcoountUI();
					uis.execute();
					break;
					
				case 4: //계좌선택
					
					uis = new UserSelectAcoountUI();
					uis.execute();
					break;
				case 0:
					uis = new LogOutUI();
					uis.execute();
					break;
				default :
					System.out.println("유효하지 않는 메뉴입니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 유저계정 메인메뉴
	 * @return
	 */
	public int menu() {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("      [1]계좌등록  [2]계좌수정  [3]계좌삭제  [4]계좌선택  [0]. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		int type = scanInt("원하는 메뉴를 선택하세요. : ");
		return type;
	}
	
	
	

}//c
