package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.UserAccDAODB;
import VO.UserVO;

public class UserService {
	
	private Scanner sc = new Scanner(System.in);
	private UserAccDAODB dao;

	
	public UserService() {
		dao = new UserAccDAODB();
	}
	
	
	/**
	 * 현재날짜와 최근 계좌만든날짜 차이.(안씀)
	 * @param res
	 * @param bank
	 * @return
	 * @throws Exception
	 */
	public int getEnroll_dtService(String res, String bank) throws Exception{

		int betdate = dao.getEnroll_dt(res, bank);
		return betdate;
	}
	
	public boolean SgetEnroll_dtService(String res, String bank) throws Exception{
		String b = dao.SgetEnroll_dt(res, bank);
		if(b==null) { // null이면 false
			return false;
		}else {
			return true;
		}
		
		
	}
	
	/**
	 * 계좌가 맞는지 확인
	 * @param col
	 * @param vov
	 * @return
	 * @throws Exception
	 */
	public boolean checkInfoService(String col, UserVO vov) throws Exception{
		UserVO uvo = dao.checkInfo(col,vov);
		if(uvo.getAccount().equals(vov.getAccount())) {	
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 비밀번호가 맞는지 확인
	 * @param col
	 * @param vov
	 * @return
	 * @throws Exception
	 */
	public boolean checkPwService(String col, UserVO vov) throws Exception{
		UserVO uvo = dao.checkInfo(col,vov);
		if(uvo.getAccpwd() != null) {
			if(uvo.getAccpwd().equals(vov.getAccpwd())) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * 전체계좌 출력서비스
	 * @param res
	 * @throws Exception
	 */
	public void showAllAccService(List<UserVO> l) throws Exception{
		List<UserVO> lst = l;
		if(lst.size()==0) {
			System.out.println("\t\t정보가 없습니다. 영업점에 문의하세요.");
			
		}else {
			System.out.println();
			System.out.println("\t\t\t\t보유한 계좌");
			System.out.println();
			for(UserVO u : lst) {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("\t[은행명] "+BankName(u.getBank_code()));
				System.out.print("\t[계좌번호] "+AccPrint(u.getAccount())+"\t");
				System.out.println("   [별칭] "+u.getAccalias());
				System.out.println("\t[잔액] "+u.getBalance());
				System.out.println("-------------------------------------------------------------------");
			}
		}
	}
	
	/**
	 * 보유한 모든 계좌 리스트 출력
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> showAllAccList(String res) throws Exception{
		List<UserVO> lst = dao.showAllAcc(res);
		return lst;
	}
	
	public UserVO myAllAcc(String col,UserVO vov) throws Exception{
		UserVO myinfo = dao.checkInfo(col, vov);
		return myinfo;
	}
	

	
	/**
	 * 은행코드->은행이름으로 변경
	 * @param bname
	 * @return
	 */
	public String BankName(String bname) {
		String bankname = bname;
		switch(bankname) {
		case "H":
			bankname = "하나은행";
			break;
		case "S":
			bankname = "신한은행";
			break;
		case "K":
			bankname = "국민은행";
			break;
		case "I":
			bankname = "기업은행";
			break;
		case "W":
			bankname = "우리은행";
			break;
		}
		return bankname;
	}
	
	/**
	 * 계좌 -와 함께 출력
	 * @param accnum
	 * @return
	 */
	public String AccPrint(String accnum) {
		String acc = accnum;
		String accNum = "";
		for (int i = 0; i<accnum.length(); i++) {
			accNum += accnum.charAt(i);
			if(i == 3 || i == 10) accNum+="-";
		}
		return accNum;
	}
	
	/**
	 * 정보수정후 출력
	 * @param col
	 * @param uv
	 * @throws Exception
	 */
	public void updateAccService(String col,UserVO uv) throws Exception{
		dao.updateInfo(col, uv);
		UserVO info = myAllAcc(col,uv);
		if(col.equals("accalias")) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("\t[은행명] "+BankName(uv.getBank_code()));
			System.out.print("\t[계좌번호] "+AccPrint(uv.getAccount())+"\t");
			System.out.println("   [별칭] "+uv.getAccalias());
			System.out.println("\t[잔액] "+uv.getBalance());
			System.out.println("-------------------------------------------------------------------");			
		}
		System.out.println("정보가 수정되었습니다.");
	}
	
	
	
	
	/**
	 * 계좌 삭제
	 * @param uv
	 * @throws Exception
	 */
	public void deleteAccountService(UserVO uv) throws Exception{ 
		System.out.println();
		System.out.println("["+BankName(uv.getBank_code())+"]"
							+AccPrint(uv.getAccount())+" : "+uv.getAccalias()
							+"  계좌가 삭제되었습니다.");
		System.out.println();
		dao.deleteAccount(uv);
	}
	
	/**
	 * 계좌생성후 출력
	 * @param uv
	 * @throws Exception
	 */
	public void InsertAccountService(UserVO uv) throws Exception{
		dao.AddAccount(uv);
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\t\t계좌가 생성되었습니다.");
		System.out.println("-------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t[은행명] "+BankName(uv.getBank_code()));
		System.out.print("\t[계좌번호] "+AccPrint(uv.getAccount())+"\t");
		System.out.println("   [별칭] "+uv.getAccalias());
		System.out.println("\t[잔액] "+uv.getBalance());
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		
	}
	
	
	/**
	 * 송금서비스사용시 계좌 선택하여 정보받아오기.
	 * @param s
	 * @param l
	 * @return
	 * @throws Exception
	 */
	public UserVO myAllAccwithSeq(String s, List<UserVO> l) throws Exception{
		Map<Integer, UserVO> myaccmap = new HashMap<Integer,UserVO>();
		UserVO myac = new UserVO();
		for(int i = 0; i <l.size(); i++) {
			myaccmap.put((i+1), l.get(i));
		}
		
		for(Map.Entry<Integer, UserVO> e : myaccmap.entrySet()) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("[ "+e.getKey()+" ] "+ BankName(e.getValue().getBank_code()));
			System.out.print("\t[계좌번호] "+AccPrint(e.getValue().getAccount())+"\t");
			System.out.println("   [별칭] "+e.getValue().getAccalias());
			System.out.println("\t[잔액] "+e.getValue().getBalance());
		}
		System.out.println("-------------------------------------------------------------------");
		
		while(true)
		{
			System.out.print("계좌를 선택하세요 : ");
			int selectAcc = sc.nextInt();
			if(selectAcc<(l.size()+1) && selectAcc >= 1) {
				myac = l.get((selectAcc-1));
				System.out.println("-------------------------------------------------------------------");
				System.out.println();
				System.out.println("[ "+BankName(myac.getBank_code())+" ] " 
									+ AccPrint(myac.getAccount())+
									" | 잔액 : " +myac.getBalance()+"원 | 계좌가 선택되었습니다.");
				System.out.println();
				System.out.println("-------------------------------------------------------------------");
				return myac;	
			}else {
				System.out.println("유효하지 않는 번호입니다.");
				continue;
			}
		}
	}
	
	
	/**
	 * 송금...!update
	 * @param uv
	 * @throws Exception
	 */
	public void UpdateBalanceService(UserVO uv) throws Exception{
		dao.UpdateBalance(uv);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
