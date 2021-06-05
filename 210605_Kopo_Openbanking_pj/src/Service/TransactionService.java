package Service;

import java.util.List;

import DAO.TranDAODB;
import VO.TranVO;
import VO.UserVO;

public class TransactionService {
	
	private TranDAODB dao;

	
	public TransactionService() {
		dao = new TranDAODB();
	}
	
	
	/**
	 * 로그기록.
	 * @throws Exception
	 */
	public void InputTransLogService(TranVO t1, TranVO t2) throws Exception{
		dao.InputTransLog(t1);
		dao.InputTransLog(t2);
		
		
	}
	
	/**
	 * 출금인지 입금인지
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String changeTcode(String s) throws Exception{
		String str = "";
		if(s.equals("0")) {
			str = "출금";
		}else {
			str = "입금";
		}
		return str;
	}
	
	/**
	 * 출금/입금 기호
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getTcode(String s) throws Exception{
		String str = "";
		if(s.equals("0")) {
			str = "-";
		}else {
			str = "+";
		}
		return str;
	}
	
	
	
	
	/**
	 * 거래내역 출력
	 * @param uv
	 * @throws Exception
	 */
	public void getAllTransLogService(UserVO uv) throws Exception{
		
		List<TranVO> tvlst = dao.getAllTransLog(uv);
		System.out.println("-------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t\t\t[ 현재잔액 ] "+uv.getBalance());
		System.out.println();
		
		if(tvlst.size()==0) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("\t거래내역이 존재하지 않습니다.");
		}else {
			System.out.println("-------------------------------------------------------------------");
			for(TranVO t: tvlst) {
				System.out.println("[ "+t.getTran_dt()+" | "+changeTcode(t.getTr_code())+" ] "
						+getTcode(t.getTr_code())+" "+t.getBalance()+"원");
			}
		}
		
	}
	
	

}
