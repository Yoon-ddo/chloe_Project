package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.AdminDAODB;
import UI.BaseUI;
import UI.LogOutUI;
import VO.LogVO;

public class AdminService {
	
	private AdminDAODB dao;
	
	public AdminService() {
		dao = new AdminDAODB();
	}
	
	
	
	/**
	 * 메뉴입력받기
	 * @return
	 * @throws Exception
	 */
	public int getMenu(String m) throws Exception {
		
		while(true) {
			int menu = Integer.parseInt(m);
			switch(menu) {
			case 1:	return menu;
			case 2:	return menu;
			case 3:	return menu;
			case 4:	return menu;
			case 0: return menu;
			default:
			return -1;
			
			}
		}
	}
	
	
	/**
	 * 유저정보 리스트 가져오기.
	 * 1.전체, 2.id검색, 3.이름검색, 4.연락처검색, 0.로그아웃
	 * @param menu
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public List<LogVO> showUserService(int menu, String i) throws Exception{
		List<LogVO> lst = new ArrayList<>();
		lst = dao.showUser(menu, i);
		return lst;
	}
	
	
	/**
	 * 유저리스트 출력
	 */
	public void PrintUser(List<LogVO> lst) throws Exception{
		
		if(lst.size()==0) {
			System.out.println("\t회원이 존재하지 않습니다.");
		}else {
			for(LogVO l : lst) {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("[ID] "+ l.getId());
				System.out.println("[이름] "+ l.getName() + "\t[성별] "+l.getGender());
				System.out.println("[주민등록번호] "+ Getrescd(l));
				System.out.println("[휴대폰번호] "+ GetPhone(l));
				
				
			}
			System.out.println("-------------------------------------------------------------------");
			System.out.println("\t총 "+lst.size()+"명의 고객이 조회되었습니다.");
		}
	}

	
	/**
	 * 주민등록번호 비식별처리
	 * @param lg
	 * @return
	 * @throws Exception
	 */
	public String Getrescd(LogVO lg) throws Exception{
		String r = lg.getRes_code();
		String rc = "";
		for(int i = 0; i<r.length(); i++) {
			
			if(i==6) {
				rc += "-";
			}else if(i>=7) {
				rc+="*";
			}else {
				rc += r.charAt(i);
			}
			
		}
		return rc;
	}
	
	/**
	 * 전화번호 형식
	 * @param lg
	 * @return
	 * @throws Exception
	 */
	public String GetPhone(LogVO lg) throws Exception{
		String p = lg.getPhone();
		String phone = "";
		
		for(int i = 0; i<p.length();i++) {
			phone += p.charAt(i);
			if(i==2 || i==6) phone += "-";
		}
		
		return phone;
	}
	

	
	
}
