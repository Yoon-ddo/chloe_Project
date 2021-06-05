package UI;

import Service.TransactionSequence;
import VO.TranVO;
import VO.UserVO;

public class UserSendMoneyUI extends BaseUI{
	
	private UserVO myAcc = new UserVO();
	private UserVO notMy = new UserVO();
	private TranVO trans = new TranVO(); // 내가 보낸정보
	private TranVO retrn = new TranVO(); // 상대방이 받은 정보
	public int transeq;
	

	@Override
	public void execute() throws Exception {
		String res = LogInUI.rescd;
		userloglst = userservice.showAllAccList(res);
		myAcc = userservice.myAllAccwithSeq(res,userloglst);
		
		while(true) {
			
			String b = scanStr("송금할 금액을 입력하세요. : ");
			System.out.println();
			boolean isNumeric1 = isValidNum(b);
			if(!isNumeric1) {
				System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
				continue;
			}else {
				int bal = Integer.parseInt(b);
				if(bal <= myAcc.getBalance() && bal > 0) {
					//System.out.println("잔액보다 송금할금액이 작음 : 정상작동");
					trans.setBalance(bal);
					retrn.setBalance(bal);
					
					transeq = TransactionSequence.getTranscd();
					trans.setTranseq(transeq);
					retrn.setTranseq(transeq);
					
					trans.setFromacc(myAcc.getAccount());
					trans.setFrombank(myAcc.getBank_code());
					
					retrn.setToacc(myAcc.getAccount());
					retrn.setTobank(myAcc.getBank_code());
					
					trans.setTr_code("0"); // 출금 : 0 | 입금 : 1
					retrn.setTr_code("1");
					
					// 내껀 빼고, 남의껀 더한다.
					myAcc.setBalance(myAcc.getBalance()-bal);
					
					//System.out.println("출금 "+myAcc);
					//System.out.println("입금 "+notMy);
					
					//입금할사람한테 계좌 입력.
					while(true) {
						
						String account = scanStr("입금할 분의 계좌번호를 입력하세요(숫자만입력하세요) :");
						System.out.println();
						boolean isNumeric = isValidNum(account);
						if(!isNumeric) {
							System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
							continue;
						} else {
							notMy.setAccount(account);
							notMy = userservice.myAllAcc("account", notMy);
							
							notMy.setBalance(notMy.getBalance()+bal);
							//System.out.println("입금 "+notMy);
						
							
							if(notMy.getAccount()!=null) {
								if(notMy.getAccount().equals(myAcc.getAccount())) {
									//내계좌랑 같으면 입금 ㄴㄴ
									System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
									continue;
								} else {
									if(notMy.getAccount().equals(account)){
										//계좌가 존재하면 상대방 계좌 등록
										trans.setToacc(account);
										trans.setTobank(notMy.getBank_code());
									
										retrn.setFromacc(account);
										retrn.setFrombank(notMy.getBank_code());
										break;									
									}
								}
								
							}else {
								System.out.println("유효하지 않은 값입니다! 다시입력하세요.");
								continue;
							}
						}
					}
					userservice.UpdateBalanceService(myAcc);
					userservice.UpdateBalanceService(notMy);
					transervice.InputTransLogService(trans, retrn);
					System.out.println("송금이 완료되었습니다.");
					break;

				}else {
					System.out.println("금액이 올바르지 않습니다.");
					continue;
				}
			}
		}
		
		
		
		

	}
	
	
	
	

}
