package guestBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import guestBook.model.GuestBook;
import jdbc.JdbcUtil;

public class GuestBookDao {

	public GuestBookDao() {

	}

//	public GuestBook selectById(Connection conn, String id) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			GuestBook guest = null;
//			if (rs.next()) {
//				guest = new GuestBook(rs.getString("id"), rs.getString("name"), rs.getString("password"),
//						rs.getString("message"));
//
//			}
//			return guest;
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}

	public void insert(Connection conn, GuestBook guest) throws SQLException {
		System.out.println("dao");
		try (PreparedStatement pstmt = conn.prepareStatement("insert into guestbook_message "
				+ " (message_id, guest_name, password, message) " + " values(message_id_seq.nextval, ?,?,?)");) {

			System.out.println(guest.getName() + guest.getPassword() + guest.getMessage());

			pstmt.setString(1, guest.getName());
			pstmt.setString(2, guest.getPassword());
			pstmt.setString(3, guest.getMessage());
			pstmt.executeUpdate();
		}
	}

	public static List messagelist(Connection conn, int firstRow, int endRow) throws SQLException {
		List<GuestBook> list = new ArrayList();
		HttpSession session = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from (select rownum rnum, a.* from guestbook_message a) where rnum >= ? and rnum <= ? order by message_id asc ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			System.out.println("query : " + query);
			GuestBook guestbook = null;
			while (rs.next()) {
				System.out.println("=====");
				guestbook = new GuestBook(rs.getString("message_id"),
						rs.getString("guest_name"),
						rs.getString("password"),
						rs.getString("message"));
				list.add(guestbook);
			}
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	public static int totalGuestBook(Connection conn) throws SQLException {
		String total = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = "select count(*) as totalGuestBook from guestbook_message";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.print("query:"+query);
			
			while(rs.next()) {
				System.out.println("list");
				total = rs.getString("totalGuestBook");
			}
			return Integer.parseInt(total);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void delete(Connection conn, GuestBook guest) throws SQLException {
		System.out.println("delete");
		try (PreparedStatement pstmt = conn.prepareStatement("delete from guestbook_message where message_id = ? ");) {
				
			System.out.println("delete==================================================");
			System.out.println();
			pstmt.setString(1, guest.getId());
			
			pstmt.executeUpdate();
		}
	}

	
	public boolean isExisted(Connection conn, String message_id, String password) {
	      boolean result = false;
	      try (PreparedStatement pstmt = conn.prepareStatement(
	            " select decode(count(*),1,'true','false') as result from guestbook_message where message_id = ? and password = ?")){
	         pstmt.setString(1, message_id);
	         pstmt.setString(2, password);
	         

	         ResultSet rs = pstmt.executeQuery();
	         rs.next();
	         result = Boolean.parseBoolean(rs.getString("result"));
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	      }
	   
	
	
//	public void update(Connection conn, GuestBook member) throws SQLException {
//		try (PreparedStatement pstmt = conn.prepareStatement(
//				"update member set name = ?, password = ? where member_id = ?")) {
//			pstmt.setString(1, member.getName());
//			pstmt.setString(2, member.getPassword());
//			pstmt.setString(3, member.getId());
//			pstmt.executeUpdate();
//		}
//	}
}
