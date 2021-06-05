package UI;

public class LogOutUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		IBankSysUI ui = new BankSysUI();
		System.out.println("-------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t   로그아웃되었습니다. 이용해주셔서 감사합니다.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
	
		ui.execute(); // 처음화면
		
		
	}

}
