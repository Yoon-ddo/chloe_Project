package VO;

public class UserVO {
	
	private String name;
	private String phone;
	private String bank_code;
	private String account;
	private String accpwd;
	private String accalias;
	private String enroll_dt;
	private int balance;
	private String res_code;
	
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVO(String name, String phone, String bank_code, String account, String accpwd, String accalias,
			String enroll_dt, int balance, String res_code) {
		super();
		this.name = name;
		this.phone = phone;
		this.bank_code = bank_code;
		this.account = account;
		this.accpwd = accpwd;
		this.accalias = accalias;
		this.enroll_dt = enroll_dt;
		this.balance = balance;
		this.res_code = res_code;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccpwd() {
		return accpwd;
	}
	public void setAccpwd(String accpwd) {
		this.accpwd = accpwd;
	}
	public String getAccalias() {
		return accalias;
	}
	public void setAccalias(String accalias) {
		this.accalias = accalias;
	}
	public String getEnroll_dt() {
		return enroll_dt;
	}
	public void setEnroll_dt(String enroll_dt) {
		this.enroll_dt = enroll_dt;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	
	@Override
	public String toString() {
		return "UserVO [name=" + name + ", phone=" + phone + ", bank_code=" + bank_code + ", account=" + account
				+ ", accpwd=" + accpwd + ", accalias=" + accalias + ", enroll_dt=" + enroll_dt + ", balance=" + balance
				+ ", res_code=" + res_code + "]";
	}
	
	
	
	
	

}
