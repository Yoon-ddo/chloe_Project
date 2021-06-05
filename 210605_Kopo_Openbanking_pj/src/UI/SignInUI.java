package UI;

import VO.LogVO;

public class SignInUI extends BaseUI{
	public static boolean isNumeric;
	private static LogVO inputinfo;
	
	public SignInUI() {
		isNumeric = false;

		inputinfo = new LogVO();
	}
	
	
	
	@Override
	public void execute() throws Exception {
		LogVO logvo = new LogVO();

		while(true) {
			String id = InputString("ID를 입력하세요 : ");
			//option.put("id", id);
			logvo = logservice.LogInService("id", id);
			if(logvo.getId()!=null) {
				System.out.println("이미 등록된 아이디입니다.");
			}
			else {
				inputinfo.setId(id);
				break;
			}
		}
		
		String pwd = scanStr("비밀번호를 입력하세요 : ");
		inputinfo.setPwd(pwd);
		String name = InputString("이름을 입력하세요 : ");
		inputinfo.setName(name);
		
		while(true) {
			String res_code = scanStr("주민등록번호 13자리를 입력하세요 : ");
			isNumeric = isNum(res_code); // 숫자인지 판별
			if(!isNumeric || res_code.length()!=13) { // 숫자가 아니면
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
				continue;
			}else {
				//option.put("res_code", res_code);
				logvo = logservice.LogInService("res_code",res_code);
				if(logvo.getRes_code()!=null) {
					System.out.println("이미 등록된 회원정보입니다.");
					continue;					
				}else {
					inputinfo.setRes_code(res_code);
					char gender = res_code.charAt(6); // 자동으로 성별 들어가도록.
					if(gender=='9'||gender=='1'||gender=='3'||gender=='5'||gender=='7') {
						gender = 'M';
					} else {
						gender = 'F';
					}
				inputinfo.setGender(String.valueOf(gender));
				break;
				}
			}
		}
		while(true) {
			String phone = scanStr("연락처를 입력하세요 : ");		
			isNumeric = isNum(phone);
			if(!isNumeric) {
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
			}else {//숫자면
				logvo = logservice.LogInService("phone",phone);
				
				if(logvo.getPhone()==null) {//값이없으면 저장.
					inputinfo.setPhone(phone);
					break;
				} else {
					if(logvo.getPhone().equals(phone)) {
						System.out.println("이미 등록된 회원정보입니다.다시입력하세요.");
						continue;						
					}
					
				}
			}
		}
		logservice.SignInputService(inputinfo);
		System.out.println("회원가입이 완료되었습니다.");

	}
	
	/**
	 * 문자만 입력할 수 있는 메소드
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String InputString(String msg) throws Exception{
		while(true) {
			String istr = scanStr(msg);
			isNumeric = isNum(istr);
			if(!isNumeric) return istr;
			else System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
		}
	}
	
	/**
	 * 숫자인지 아닌지 판별.
	 * @param i
	 * @return
	 */
	public static boolean isNum(String i) {
		isNumeric = i.chars().allMatch(Character::isDigit);
		return isNumeric;
	}


}
