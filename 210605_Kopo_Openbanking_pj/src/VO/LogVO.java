package VO;

public class LogVO {
	
	private String id;
	private String name;
	private String pwd;
	private String res_code;
	private String gender;
	private String phone;
	
	
	
	public LogVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LogVO(String id, String name, String pwd, String res_code, String gender, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.res_code = res_code;
		this.gender = gender;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "LogVO [id=" + id + ", name=" + name + ", pwd=" + pwd + ", res_code=" + res_code + ", gender=" + gender
				+ ", phone=" + phone + "]";
	}
	
	
	
	
	
	
	

}
