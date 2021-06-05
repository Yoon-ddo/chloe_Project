package kopolibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin extends BookData implements kopoLib{
	Scanner sc = new Scanner(System.in);
	int menuNum;
	int mNum;
	Map<String, ArrayList> EnrollU = new HashMap<>();
	
	Admin(){
		EnrollU = super.EnrollUser;
	}
	
	
	public void BookCntInfo() {
		System.out.println("-----------------------");
		System.out.println("  보유 권 수 : " + super.EnrollBooks.size() + "개");
		System.out.println("-----------------------");
	}
	
	public void accCntInfo() {
		System.out.println("-----------------------");
		System.out.println("  총 계정 수 : " + super.EnrollUser.size() + "개");
		System.out.println("-----------------------");
	}
	
	public void Bookprint(String bno) {
		System.out.println("  책 제목\t: "+super.EnrollBooks.get(bno).get(1));
		System.out.println("  저자\t: "+super.EnrollBooks.get(bno).get(2));
		System.out.println("  출판사\t: "+super.EnrollBooks.get(bno).get(3));
		System.out.println("  출판날짜\t: "+super.EnrollBooks.get(bno).get(4));
		System.out.println("  장르\t: "+super.EnrollBooks.get(bno).get(5));
		System.out.println("----------------------");
	}
	public void Userprint(String mapkey) {
		System.out.println(" ID\t: "+mapkey);
        System.out.println(" 이름\t: "+super.EnrollUser.get(mapkey).get(2));
        System.out.println(" 생년월일\t: "+super.EnrollUser.get(mapkey).get(3));
        System.out.println(" 연락처\t: "+super.EnrollUser.get(mapkey).get(4));
        System.out.println("---------------------------");
	}
	
	
	
	// 도서 등록	
	public void insertBookList() {
		while(true) {
			System.out.println("--------------------");
			String bookNo = super.getStr("번호를 입력하세요 : ");
			String bookTitle = super.getStr("제목을 입력하세요 : ");
			String bookAuthor = super.getStr("저자를 입력하세요 : ");
			String bookPublisher = super.getStr("출판사를 입력하세요 : ");
			String bookDate = super.getStr("출판날짜를 입력하세요 : ");
			String bookGenre = super.getStr("장르를 입력하세요 : ");
			System.out.println("--------------------");
			
			ArrayList<String> bookList = new ArrayList<String>();
			
			bookList.add(bookNo);
			bookList.add(bookTitle);
			bookList.add(bookAuthor);
			bookList.add(bookPublisher);
			bookList.add(bookDate);
			bookList.add(bookGenre);
			
			mNum = super.getNum("저장하시겠습니까? (1.예 | 2.아니오 )");
			if (mNum == 1) {
				System.out.println("*** "+bookTitle+" 이(가) 저장되었습니다! ***");
				super.EnrollBooks.put(bookNo, bookList);
				super.notice.add(bookAuthor+" - "+bookTitle+"이(가) 우리 도서관에 입고되었습니다.");
				break;
			} else {
				System.out.println("*** 다시 입력하세요 ! ***");
				continue;
			}
		}
	}
	
	//도서 검색
	@Override
	public void findBook() {
		while(true) {
			mNum = super.getNum("* 메뉴를 선택하세요 (1. 책번호 | 2. 전체조회 | 3. 돌아가기 ) : ");
			if(mNum == 1) {
				String bno = super.getStr("책 번호를 입력하세요 : ");
				if (super.EnrollBooks.containsKey(bno) == true) {
					Bookprint(bno);
					mNum = super.getNum("* 삭제하시겠습니까? (1. 아니오 | 2. 예 ) : ");
					if(mNum == 1) {
						break;
					} else if(mNum == 2) {
						System.out.println("삭제되었습니다.");
						String mess = super.EnrollBooks.get(bno).get(1)+" - "+super.EnrollBooks.get(bno).get(2)+"이(가) 우리 도서관에 더이상 존재하지 않게 되었습니다.";
						super.notice.add(mess);
						super.EnrollBooks.remove(bno);
						BookCntInfo();
						break;
					} else {
						System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
						continue;
					}
				} else {
					System.out.println("* 유효하지 않은 번호입니다. 다시입력하세요! *");
					continue;
				}
			} else if(mNum == 2) {
				BookCntInfo();
				for(String key : super.EnrollBooks.keySet()) {
					System.out.println("[ "+key+" ]");
					Bookprint(key);
				}
			} else if(mNum == 3) {
				break;
			} else {
				System.out.println("* 유효하지 않은 번호입니다. 다시입력하세요! *");
				continue;
			}
		}
		
	}
	
	//1. 도서관리
	public void bookmanage() {
		while(true) {
			this.menuNum = super.getNum("메뉴를 입력하세요. (1.조회·삭제 | 2.추가 | 0.취소) : ");
			if(menuNum == 1) {
				findBook();
			}else if(menuNum == 2) {
				while(true) {
					insertBookList();
					BookCntInfo();
					int rollback = super.getNum("계속 추가하시겠습니까?(1.예 | 2.아니요) : ");
					if(rollback == 1) {
						continue;
					}else if(rollback == 2){
						break;
					}
				}
			}else if(menuNum == 0){
				System.out.println("*** 도서 관리를 종료합니다. ***");
				break;
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}
		}
	}
	
	//2. 계정관리
	public void accountmanage() {
		while(true) {
			this.menuNum = super.getNum("* 메뉴를 입력하세요 (1. 조회·삭제 | 2. 전체조회 | 0. 되돌아가기) : ");
			if(menuNum == 1) {
				String acciD = getStr("조회할 ID를 입력하세요 : ");
				if (super.EnrollUser.containsKey(acciD) == true) {
					System.out.println("---------------------------");
					Userprint(acciD);
			        int delNum = super.getNum(acciD+" 계정을 삭제하시겠습니까? (1. 예 | 2. 아니오)");
			        if (delNum == 1) {
			        	System.out.println("삭제되었습니다.");
			        	super.EnrollUser.remove(acciD);
			        } else if (delNum == 2) {
			        	System.out.println("취소되었습니다.");
			        	break;
			        } else {
			        	System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
			        	continue;
			        }
				}else {
					System.out.println("* 유효하지 않은 ID입니다. 다시 입력하세요! *");
		        	continue;
				}
				
			} else if(menuNum == 2) {
				accCntInfo();
				int cnt=0;
				for (String mapkey : super.EnrollUser.keySet()){
					cnt++;
					System.out.println("[ " + cnt + " ]");
					Userprint(mapkey);
			    } 
			}else if(menuNum == 0) {
			    break;
			}else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}
				
			}
		}

	
	@Override
	public void welcome() {
		System.out.println("==========[ 메뉴를 선택하세요 ]==========");
		System.out.println(" [1] 도서 관리  [2] 계정 관리  [0] 로그아웃");
		System.out.println("====================================");
		
	}


	@Override
	public void main() {
		while(true) {
			welcome();
			this.menuNum = super.getNum();
			
			if(menuNum == 1) {
				System.out.println("[1] 도서 관리");
				bookmanage();
			} else if(menuNum == 2) {
				System.out.println("[2] 계정 관리");
				accountmanage();
				
			} else if(menuNum == 0) {
				System.out.println("*** 관리자 계정에서 로그아웃되었습니다. ***");
				super.adminloginfo=false;
				break;
				
			} else {
				System.out.println("* 유효하지 않은 메뉴입니다. 다시 입력하세요! *");
				continue;
			}
		}
		
	}
	


}
