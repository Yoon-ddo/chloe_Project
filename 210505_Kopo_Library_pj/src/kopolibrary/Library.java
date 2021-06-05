package kopolibrary;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library extends BookData implements kopoLib {
	Scanner sc = new Scanner(System.in);
	Admin admin = new Admin();
	User user = new User();
	int getMenu;
	Map<String, String> adminlog = new HashMap<>();
	
	
	
	
	public Library() {
		adminlog.put("scott", "tiger");
	}
	
	@Override
	public void welcome() {
		System.out.println("===================================");
		System.out.println("  KOPO 전자 도서관에 오신 것을 환영합니다.");
		System.out.println("===================================");
		System.out.println("   [1] 회원가입\t [4] 공지사항");
		System.out.println("   [2] 로그인\t [5] 종료");
		System.out.println("   [3] 도서검색\t ");
		System.out.println("===================================");
	}
	
	
	
	public void SignUp() {
		while(true) {
			ArrayList<String> loglst = new ArrayList();
			System.out.println("<계정정보>");
			String iD = super.getStr(" 사용할 ID를 입력하세요.  :  ");
			if(super.EnrollUser.containsKey(iD) == true) {
				System.out.println("중복된 ID 입니다. 다시입력하세요.");
				continue;
			}
			loglst.add(iD);
			String pW = super.getStr(" 사용할 비밀번호를 입력하세요.  :  ");
			loglst.add(pW);
			
			System.out.println("<인적사항>");
			String Uname = super.getStr(" 성함을 입력하세요.  :  ");
			loglst.add(Uname);
			String Ubirth = super.getStr(" 생년월일 6자리를 입력하세요.  :  ");
			loglst.add(Ubirth);
			String Uphone = super.getStr(" 연락처를 입력하세요.  :  ");
			loglst.add(Uphone);
			super.EnrollUser.put(iD, loglst);
	
			System.out.println("-------- 등록되었습니다 --------");
			System.out.println();
			break;
	
		}
	}
	
	public void SignIn() {
		
		while(true) {
			String ID = super.getStr("ID : ");
			String PW = super.getStr("Password : ");
			
			if(super.EnrollUser.containsKey(ID) == true) {
				if(PW.equals(super.EnrollUser.get(ID).get(1))) {
					user.myid(ID);
					System.out.println("*** [ "+super.EnrollUser.get(ID).get(2)+" ] 고객님 환영합니다! ***");
					super.userloginfo = true;
					break;
				} else {
					System.out.println("다시입력하세요.");
					continue;
				}
			}else if(this.adminlog.containsKey(ID)==true) {
				if(PW.equals(this.adminlog.get(ID))){
					System.out.println("*** 관리자계정으로 로그인하였습니다.***");
					super.adminloginfo = true;
					break;
				} else {
					System.out.println("다시입력하세요.");
					continue;
				}
			}else {
				System.out.println("다시입력하세요.");
				continue;
				}
			} 
		}
	
	public void notice() {
		for (int n=0; n<super.notice.size();n++) {
			System.out.println("[ "+(n+1)+" ] "+super.notice.get(n));
		}
	}
	
	@Override
	public void findBook() {
		while(true) {
			this.getMenu = super.getNum("1. 키워드 검색 | 2. 전체 조회 |  0. 취소 : ");
			if(getMenu == 1) {
				String keyInfo = super.getStr("검색어를 입력하세요. : ");
				if(super.EnrollBooks.isEmpty()) {
					System.out.println("검색결과가 없습니다.");
				}else {
					for (String key : super.EnrollBooks.keySet()) {
						if(super.EnrollBooks.get(key).contains(keyInfo)) {
							//System.out.println("------[ "+key+" ]------");
							user.Bookprint(key);
						} 
					}	
				}
				
			}else if(getMenu == 2) {
				for (String key : super.EnrollBooks.keySet()) {
					//System.out.println("------[ "+key+" ]------");
					user.Bookprint(key);
				}
			}else if(getMenu == 0) {
				break;
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. *");
				continue;
			}
		}

	}
	
	
	@Override
	public void main() {
		
		while(true){
			welcome();
			System.out.print("메뉴를 입력하세요 : ");
			this.getMenu = getNum();
			if(getMenu == 1) {
				System.out.println("**** [1] 회원가입 ****");
				SignUp();
				continue;
			}else if(getMenu == 2) {
				System.out.println("**** [2] 로그인 ****");
				SignIn();
				if (super.adminloginfo==true) {
					admin.main();
				} else if(super.userloginfo == true) {
					user.main();
				}
				continue;
			}else if(getMenu == 3) {
				System.out.println("**** [3] 도서검색 ****");
				findBook();
			}else if(getMenu == 4) {
				System.out.println("**** [4] 공지사항 ****");
				notice();
			}else if(getMenu == 5) {
				System.out.println("이용해주셔서 감사합니다.");
				break;
				
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}
		}
		
	}
	
	

}
