package kopolibrary;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class User extends BookData implements kopoLib{
	Scanner sc = new Scanner(System.in);
	int menuNum;
	String myID;
	ArrayList<String> storebook = new ArrayList<String>(); // 내가 대여한 책 번호저장
	
	
	public User() {
		
	}
	
	public void deleteData(String retNum, int idx) {
		super.EnrollBooks.get(retNum).remove(idx);
	}
	
	public void myid(String id) {
		this.myID = id;
	}
	public void Userprint(String mapkey) {
		System.out.println(" ID\t: "+mapkey);
        System.out.println(" 이름\t: "+super.EnrollUser.get(mapkey).get(2));
        System.out.println(" 생년월일\t: "+super.EnrollUser.get(mapkey).get(3));
        System.out.println(" 연락처\t: "+super.EnrollUser.get(mapkey).get(4));
        System.out.println("---------------------------");
	}
	
	public void Bookprint(String bno) {
		System.out.println("  책 제목\t: "+super.EnrollBooks.get(bno).get(1));
		System.out.println("  저자\t: "+super.EnrollBooks.get(bno).get(2));
		System.out.println("  출판사\t: "+super.EnrollBooks.get(bno).get(3));
		System.out.println("  출판날짜\t: "+super.EnrollBooks.get(bno).get(4));
		System.out.println("  장르\t: "+super.EnrollBooks.get(bno).get(5));
		System.out.println("----------------------");
	}
	
	public void modifyInfo(String msg , int idx) {
		String txt = super.getStr(msg);
		super.EnrollUser.get(myID).set(idx,txt);
		Userprint(myID);
	}
	
	@Override
	public void findBook() {
		while(true) {
			this.menuNum = super.getNum("1. 책 번호로 검색 | 2. 책 제목으로 검색 | 0. 취소 : ");
			if (menuNum == 1) {
				String bookNum = super.getStr("책 번호를 입력하세요 : ");
				if (super.EnrollBooks.containsKey(bookNum) == true){
					Bookprint(bookNum);
					if(super.EnrollBooks.get(bookNum).size() == 6) {
						System.out.println("대여 가능");

						this.menuNum = super.getNum("대여하시겠습니까? 1.예 | 2.아니오 : ");
						if(menuNum == 1) {
							super.EnrollBooks.get(bookNum).add("->대여중");
							super.EnrollBooks.get(bookNum).add(myID);
							this.storebook.add(bookNum);
						}else if(menuNum == 2) {
							continue;
						}  else {
							System.out.println("* 유효하지 않은 메뉴입니다. *");
							continue;
						}
		
					}else if(super.EnrollBooks.get(bookNum).size() == 8) {
						System.out.println(super.EnrollBooks.get(bookNum).get(6));
					}
				} else {
					System.out.println("존재하지 않는 책입니다.");
					continue;
				}
				
			}else if(menuNum == 2) {
				String findinfo = super.getStr("책 제목을 입력하세요 : ");
				for (String key : super.EnrollBooks.keySet()) {
					if(super.EnrollBooks.get(key).contains(findinfo)) {
						System.out.println("------[ "+key+" ]------");
						Bookprint(key);
						if(super.EnrollBooks.get(key).size() == 6) {
							System.out.println("-> 대여 가능");
						} else if(super.EnrollBooks.get(key).size()==8) {
							System.out.println(super.EnrollBooks.get(key).get(6));	
						}
					}
				}
				
				int rentn = super.getNum("대여하시겠습니까? 1.예 | 2.아니오 : ");
				if(rentn == 1) {
					String bookNum = super.getStr("대여할 책 번호를 입력하세요 : ");
					super.EnrollBooks.get(bookNum).add("->대여중");
					super.EnrollBooks.get(bookNum).add(myID);
					this.storebook.add(bookNum);
					continue;
				}else if(rentn == 2) {
					continue;
				}  else {
					System.out.println("* 유효하지 않은 메뉴입니다. *");
					continue;
				}
				
			}else if(menuNum == 0) {
				break;
			} else {
				System.out.println("없는 메뉴입니다. 다시입력해주세요.");
				continue;
			}
			
		}
		
	}
	
	
	public void mypage() {
		System.out.println("---------------------------");
		Userprint(this.myID);
		while(true) {
			this.menuNum = super.getNum("1. 내정보 수정 | 2. 도서 반납 | 0. 되돌아가기 : ");
			if(menuNum == 1) {
				String inputPw = super.getStr("현재 비밀번호를 입력하세요 : ");
				if (inputPw.equals(super.EnrollUser.get(myID).get(1))) {
					while(true) {
						int meNum = super.getNum("수정할 내용을 선택하세요.(1. 이름 | 2. 생년월일 | 3. 연락처 | 4. 비밀번호 | 0. 취소)");
						if(meNum == 1) {
							modifyInfo("수정할 이름을 입력하세요. : " , 2);
						}else if(meNum == 2) {
							modifyInfo("수정할 생년월일을 입력하세요. : " , 3);
						}else if(meNum == 3) {
							modifyInfo("수정할 연락처를 입력하세요. : " , 4);
						}else if(meNum == 4){
							modifyInfo("수정할 비밀번호를 입력하세요. : " , 1);
						}else if(meNum == 0) {
							break;
						}else {
							System.out.println("없는 메뉴입니다. 다시입력해주세요.");
							continue;
						}
					}
				} else {
					System.out.println("비밀번호가 틀립니다.");
					break;
				}
			} else if (menuNum == 2){ 
				System.out.println("-----------------------");
				System.out.println("  대여 권 수 : " + this.storebook.size() + "개");
				System.out.println("-----------------------");
				if(this.storebook.size() == 0) {
					System.out.println("반납할 책이 없습니다.");
					break;
				}else {
					for (int i = 0; i<storebook.size();i++) {
						String rntNum = storebook.get(i);
						System.out.println("--------[ "+rntNum+" ]--------");
						Bookprint(rntNum);
					}
					String retNum = super.getStr("반납할 도서 번호를 입력하세요(0을 누르면 되돌아갑니다) : ");
					Bookprint(retNum);
					if(retNum.equals("0")) {
						break;
					}else {
						while(true) {
							int returnNum = super.getNum("반납하시겠습니까? (1.예 | 2.아니오) : ");
							if(returnNum == 1) {
								System.out.println("반납이 완료되었습니다. 이용해주셔서 감사합니다.");
								deleteData(retNum, 7);
								deleteData(retNum, 6);
								storebook.remove(retNum);
								break;
							} else if(returnNum == 2) {
								break;
							} else {
								System.out.println("유효하지 않은 번호입니다. 다시 입력해주세요");
								continue;
							}
							
						}
						
					}
					
				}
			} else if(menuNum == 0) {
				break;
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}
		}
	}
	
	
	@Override
	public void welcome() {
		System.out.println("===========[ 메뉴를 선택하세요 ]===========");
		System.out.println(" [1] 도서 검색  [2] 마이페이지  [0] 로그아웃");
		System.out.println("=======================================");
		
	}

	
	
	@Override
	public void main() {
		while(true) {
			welcome();
			this.menuNum = super.getNum();

			if(menuNum == 1) {
				System.out.println("[1] 도서 검색");
				findBook();

			} else if(menuNum == 2) {
				System.out.println("[2] 마이페이지");
				mypage();
				
			} else if(menuNum == 0) {
				System.out.println("*** 로그아웃되었습니다. ***");
				super.userloginfo = false;
				break;
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}	
		}
		
	}
	

}
