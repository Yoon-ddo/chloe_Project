package UI;

public class UserSelectAcoountUI extends BaseUI{
	
	public UserUI usui = new UserUI();

	@Override
	public void execute() throws Exception {
		while(true) {
			try {
				BaseUI uis = null;
				int menutype = menu();
				switch(menutype) {
				case 1:
					//System.out.println("내역조회");
					uis = new UserTransLogUI();
					uis.execute();
					break;
				case 2:
					//System.out.println("송금");
					uis = new UserSendMoneyUI();
					uis.execute();
					break;
				case 0:
					//System.out.println("bye~");
					usui.execute();
					break;
				default :
					System.out.println("유효하지 않는 메뉴입니다.");
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	

	public int menu() throws Exception {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("              [1]내역조회     [2]송금     [0]되돌아가기");
		System.out.println("-------------------------------------------------------------------");
		int type = scanInt("메뉴를 선택하세요 : ");
		System.out.println();
		return type;
				
		
	}

}
