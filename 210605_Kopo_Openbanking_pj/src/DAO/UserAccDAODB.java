package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionFactory;
import Util.JDBCClose;
import VO.UserVO;

public class UserAccDAODB {
	
	
	/**
	 * 날짜 차이 불러오기.(안씀)
	 * @param res
	 * @param bank
	 * @return
	 * @throws Exception
	 */
	public int getEnroll_dt(String res,String bank) throws Exception{
		
		int enroll = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select trunc(months_between(sysdate,enroll_dt),0)as enroll_dt  ");
			sql.append("from accountdb ");
			sql.append("where res_code = ? and bank_code = ? and rownum = 1 ");
			sql.append("order by enroll_dt desc ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, res);
			pstmt.setString(2, bank);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				enroll = rs.getInt("enroll_dt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return enroll;
		
	}
	
	
	public String SgetEnroll_dt(String res,String bank) throws Exception{
			
			String enroll=null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = new ConnectionFactory().getConnection();
				StringBuilder sql = new StringBuilder();
				
				sql.append("select trunc(months_between(sysdate,enroll_dt),0)as enroll_dt  ");
				sql.append("from accountdb ");
				sql.append("where res_code = ? and bank_code = ? and rownum = 1 ");
				sql.append("order by enroll_dt desc ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, res);
				pstmt.setString(2, bank);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					enroll = rs.getString("enroll_dt");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return enroll;
			
		}
	
	
	/**
	 * 주민번호로 조회하여 전체 계좌정보 출력
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> showAllAcc(String res) throws Exception{
		List<UserVO> uvo = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select name, phone, bank_code, account, accpwd, accalias, enroll_dt, balance, res_code ");
			sql.append("from accountdb ");
			sql.append("where res_code = ? ");
			sql.append("order by balance desc ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, res);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String bank_code = rs.getString("bank_code");
				String account = rs.getString("account");
				String accpwd = rs.getString("accpwd");
				String accalias = rs.getString("accalias");
				String enroll_dt = rs.getString("enroll_dt");
				int balance = rs.getInt("balance");
				String res_code = rs.getString("res_code");
				
				UserVO inputvo = new UserVO(name, phone, bank_code, account, accpwd, accalias, enroll_dt, balance, res_code);
				uvo.add(inputvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		return uvo;
	}
	
	
	
	
	/**
	 * 별칭/비밀번호변경
	 * @param col
	 * @param uv
	 * @throws Exception
	 */
	public void updateInfo(String col, UserVO uv) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			switch(col) {
			case "accpwd":
				sql.append("update accountdb set accpwd = ? ");
				sql.append("where account = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, uv.getAccpwd());
				pstmt.setString(2, uv.getAccount());
				pstmt.executeUpdate();
				break;
				
			case "accalias":
				sql.append("update accountdb set accalias = ? ");
				sql.append("where account = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, uv.getAccalias());
				pstmt.setString(2, uv.getAccount());
				pstmt.executeUpdate();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	/**
	 * 컬럼별 정보 입력하면 조회
	 * @param col
	 * @param u
	 * @return
	 * @throws Exception
	 */
	public UserVO checkInfo(String col, UserVO u) throws Exception{
		UserVO uvo = new UserVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select name, phone, bank_code, account, accpwd, accalias, enroll_dt, balance, res_code ");
			sql.append("from accountdb ");

			switch(col){
			case "name":
				sql.append("where name = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getName());
				break;
			case "phone":
				sql.append("where phone = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getPhone());
				break;
			case "bank_code":
				sql.append("where bank_code = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getBank_code());
				break;
			case "account":
				sql.append("where account = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getAccount());
				break;
			case "accpwd":
				sql.append("where accpwd = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getAccpwd());
				break;
			case "accalias":
				sql.append("where accalias = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getAccalias());
				break;
			case "enroll_dt":
				sql.append("where enroll_dt = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getEnroll_dt());
				break;
			case "balance":
				sql.append("where balance = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, u.getBalance());
				break;
			case "res_code":
				sql.append("where res_code = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, u.getRes_code());
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String bank_code = rs.getString("bank_code");
				String account = rs.getString("account");
				String accpwd = rs.getString("accpwd");
				String accalias = rs.getString("accalias");
				String enroll_dt = rs.getString("enroll_dt");
				int balance = rs.getInt("balance");
				String res_code = rs.getString("res_code");
				uvo = new UserVO(name, phone, bank_code, account, accpwd, accalias, enroll_dt, balance, res_code);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		return uvo;
	}
	
	
	/**
	 * 계좌번호를 받아서 삭제하는쿼리.
	 * @param u
	 * @throws Exception
	 */
	public void deleteAccount(UserVO u) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
			
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from accountdb ");
			sql.append("where account = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, u.getAccount());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	
	/**
	 * 계좌 추가.
	 * @param vo
	 * @throws Exception
	 */
	public void AddAccount(UserVO vo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into accountdb(name, phone, bank_code, accpwd, account, accalias, balance, res_code) ");
			sql.append(" values(?, ?, ?, ?, ?, ?, ?, ? ) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getBank_code());
			pstmt.setString(4, vo.getAccpwd());
			pstmt.setString(5, vo.getAccount());
			pstmt.setString(6, vo.getAccalias());
			//pstmt.setString(7, vo.getEnroll_dt());
			pstmt.setInt(7, vo.getBalance());
			pstmt.setString(8, vo.getRes_code());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
	}
	
	
	/**
	 * 계좌 잔액 업데이트!
	 * @param vo
	 * @throws Exception
	 */
	public void UpdateBalance(UserVO vo) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update accountdb set balance = ? ");
			sql.append("where account = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, vo.getBalance());
			pstmt.setString(2, vo.getAccount());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
	}
	


	
	
	
}//c	

