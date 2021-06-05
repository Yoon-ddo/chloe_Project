package UI;

import VO.UserVO;

public class UserTransLogUI extends BaseUI{
	
	private UserVO myAcc = new UserVO();

	@Override
	public void execute() throws Exception {
		String res = LogInUI.rescd;
		userloglst = userservice.showAllAccList(res);
		myAcc = userservice.myAllAccwithSeq(res,userloglst);
		transervice.getAllTransLogService(myAcc);
		
	}

}
