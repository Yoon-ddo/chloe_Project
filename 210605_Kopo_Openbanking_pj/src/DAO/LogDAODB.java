package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionFactory;
import Util.JDBCClose;
import VO.LogVO;

public class LogDAODB {

	private LogVO logvo;
	private List<LogVO> loglst;
	
	public LogDAODB() {
		logvo = new LogVO();
		loglst = new ArrayList<>();
	}
	
	
	public LogVO GetLogInfo(String key, String val) throws Exception{
		LogVO lgv = new LogVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("select id, pwd, name, res_code, gender, phone ");
			sql.append(" from userinfo ");

			switch(key) {
				case "id":
					sql.append(" where id = ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, val);
					break;
				case "res_code":
					sql.append(" where res_code = ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, val);
					break;
				case "phone":
					sql.append(" where phone = ? ");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.setString(1, val);
					break;
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String getid = rs.getString("id");
				String getpwd = rs.getString("pwd");
				String getname = rs.getString("name");
				String getrescd = rs.getString("res_code");
				String getgender = rs.getString("gender");
				String getphone = rs.getString("phone");
				lgv = new LogVO(getid, getname, getpwd, getrescd, getgender, getphone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		return lgv;
	}
	
	public LogVO SignInput(LogVO newlog) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into userinfo(id, pwd, name, res_code, gender, phone) ");
			sql.append(" values(?, ?, ?, ?, ?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, newlog.getId());
			pstmt.setString(2, newlog.getPwd());
			pstmt.setString(3, newlog.getName());
			pstmt.setString(4, newlog.getRes_code());
			pstmt.setString(5, newlog.getGender());
			pstmt.setString(6, newlog.getPhone());

			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		return logvo;
	}
	

}//c
