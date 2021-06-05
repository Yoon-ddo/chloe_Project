package UI;

public class ExitUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("-------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t   통합계좌 관리 시스템을 종료합니다. 이용해주셔서 감사합니다.");
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.exit(0);
		
	}

}
