package UI;

import java.util.ArrayList;
import java.util.List;

import VO.LogVO;

public class AdminUI extends BaseUI{
	

	@Override
	public void execute() throws Exception {
		List<LogVO> lst = new ArrayList<>();
		
		printMenu();
		BaseUI ui = null;
		while(true) {
			String m = scanStr("원하는 메뉴를 선택하세요. : ");
			boolean isNumeric = isValidNum(m);
			if(!isNumeric) {
				System.out.println("유효하지 않은 값입니다. 다시 입력하세요!");
				continue;
			}else {
				int menu = adminservice.getMenu(m);
				switch(menu) {
				case 0 :
					ui = new LogOutUI();
					ui.execute();
					break;
				case 1:
					lst = adminservice.showUserService(menu, "");
					adminservice.PrintUser(lst);
					break;
				case 2:
					String id = scanStr("id를 입력하세요. : ");
					lst = adminservice.showUserService(menu, id);
					adminservice.PrintUser(lst);
					break;
				case 3:
					String name = scanStr("이름을 입력하세요. : ");
					lst = adminservice.showUserService(menu, name);
					adminservice.PrintUser(lst);
					break;
				case 4:
					while(true) {
						String num = scanStr("연락처를 입력하세요(숫자만 입력) : ");
						boolean isNum = isValidNum(num);
						if(!isNum) {
							System.out.println("유효하지 않은 값입니다. 다시입력하세요!");
							continue;
						}else {
							lst = adminservice.showUserService(menu, num);
							adminservice.PrintUser(lst);
							break;
						}
					}
					
				default : System.out.println("유효하지 않은 값입니다. 다시입력하세요!");
				
				}
			}
			
			
		}
		
		
		
		//adminservice.showUserService();
		
	}
	
	public void printMenu() throws Exception{
		System.out.println("-------------------------------------------------------------------");
		System.out.println("  [1]전체고객조회   [2]ID로 조회   [3]이름으로 조회 [4]연락처로 조회   [0]. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
	
	

}
