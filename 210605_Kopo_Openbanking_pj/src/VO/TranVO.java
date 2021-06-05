package VO;

import java.util.Date;

public class TranVO {
	
	private String fromacc;
	private String frombank;
	private Date tran_dt;
	private String tr_code;
	private int transeq;
	private int balance;
	private String toacc;
	private String tobank;
	
	public TranVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TranVO(String fromacc, String frombank, Date tran_dt, String tr_code, int transeq, int balance, String toacc,
			String tobank) {
		super();
		this.fromacc = fromacc;
		this.frombank = frombank;
		this.tran_dt = tran_dt;
		this.tr_code = tr_code;
		this.transeq = transeq;
		this.balance = balance;
		this.toacc = toacc;
		this.tobank = tobank;
	}
	public String getFromacc() {
		return fromacc;
	}
	public void setFromacc(String fromacc) {
		this.fromacc = fromacc;
	}
	public String getFrombank() {
		return frombank;
	}
	public void setFrombank(String frombank) {
		this.frombank = frombank;
	}
	public Date getTran_dt() {
		return tran_dt;
	}
	public void setTran_dt(Date tran_dt) {
		this.tran_dt = tran_dt;
	}
	public String getTr_code() {
		return tr_code;
	}
	public void setTr_code(String tr_code) {
		this.tr_code = tr_code;
	}
	public int getTranseq() {
		return transeq;
	}
	public void setTranseq(int transeq) {
		this.transeq = transeq;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getToacc() {
		return toacc;
	}
	public void setToacc(String toacc) {
		this.toacc = toacc;
	}
	public String getTobank() {
		return tobank;
	}
	public void setTobank(String tobank) {
		this.tobank = tobank;
	}
	@Override
	public String toString() {
		return "TranVO [fromacc=" + fromacc + ", frombank=" + frombank + ", tran_dt=" + tran_dt + ", tr_code=" + tr_code
				+ ", transeq=" + transeq + ", balance=" + balance + ", toacc=" + toacc + ", tobank=" + tobank + "]";
	}
	
	
	
	
	
	
	

}
