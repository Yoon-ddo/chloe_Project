package UI;

import Service.Sequence;
import VO.UserVO;

public class UserMakeAccountUI extends LogInUI{
	
	public static UserVO vov;
	
	public UserMakeAccountUI() {
		vov = new UserVO();
	}
	
	@Override
	public void execute() throws Exception {
		String res = LogInUI.rescd;

		BaseUI.log = logservice.LogInService("res_code",rescd); 
		//System.out.println(BaseUI.log);
		
		//은행코드
		String bankName = bankCode();
		
		int between = userservice.getEnroll_dtService(res, bankName);
		boolean Sget = userservice.SgetEnroll_dtService(res, bankName);
		
		if(Sget == true) {
			System.out.println("이번 달 계좌 생성 제한 수를 초과하였습니다.");
			System.out.println("영업점에 문의하십시오.");
			System.out.println("-------------------------------------------------------------------");				
		}else {
			
			vov.setBank_code(bankName);
			//계좌번호(자동입력)
			int h = Sequence.getAcc();
			String str = String.format("%013d", h);
			vov.setAccount(str);
			
			//이름, 전화번호, 주민번호는 알아서 받아옴.
			vov.setName(BaseUI.log.getName());
			vov.setPhone(BaseUI.log.getPhone());
			vov.setRes_code(BaseUI.log.getRes_code());
			
			
			// 돈
			while(true) {
				String b = scanStr("입금할 금액을 입력하세요 (1000원 이상부터 입금가능합니다.) : ");
				boolean isNumeric1 = isValidNum(b);
				if(!isNumeric1) {
					System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
					continue;
				}else {
					int bal = Integer.parseInt(b);
					if(bal>=1000) {
						vov.setBalance(bal);
						break;
					}else {
						System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
						continue;
					}
				}
				
			}
			
			//비밀번호
			String pws = SetPw();
			vov.setAccpwd(pws);
			
			// 등록일 - 생략.
			//계좌별칭
			String ali = scanStr("사용할 계좌 별칭을 입력하세요 : ");
			vov.setAccalias(ali);
			
			userservice.InsertAccountService(vov);
		}
		
	}
	
	
	public String bankCode() throws Exception {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("       [1]하나은행  [2]신한은행  [3]국민은행  [4]기업은행  [5]우리은행");
		System.out.println("-------------------------------------------------------------------");
		while(true) {
			String bankcd = scanStr("계좌를 개설할 은행을 선택하세요 : ");
			boolean chnum = isValidNum(bankcd);
			if(!chnum) {
				System.out.println("유효하지 않은 값입니다.");
				continue;
			}else {
				int bcd = Integer.parseInt(bankcd);
				//String bankcode = "";
				switch(bcd) {
				case 1:
					return "H";
				case 2:
					return "S";
				case 3:
					return "K";
				case 4:
					return "I";
				case 5:
					return "W";	
				}
				
			}
				
		}
	}
	
	public String SetPw() throws Exception{
		while(true) {
			String pw = scanStr("설정할 비밀번호 4자리를 입력하세요 : ");
			boolean isNumber = isValidNum(pw);
			if(!isNumber) {
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
				continue;
			} else {
				if(pw.length()!=4) {
					System.out.println("유효한 비밀번호가 아닙니다.");
					continue;
				}else {
					return pw;
				}
			}
		}
	}


}//c
