package guestBook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import guestBook.dao.GuestBookDao;
import guestBook.model.GuestBook;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ListService {
	private static GuestBookDao guestDao = new GuestBookDao();
	private static final int message_per_page = 3;
	private static int totalPage;

	public List list(int pageNumber) {
		GuestBookRequest listReq = new GuestBookRequest();
		List listGuestbook = null;
		Connection conn = null;
		int currentPagenumber = pageNumber;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int totalCount = GuestBookDao.totalGuestBook(conn);
			int firstRow = 0;
			int endRow = 0;
			
			if(totalCount>0) {
				firstRow = (pageNumber - 1)*message_per_page +1;
				endRow = firstRow + message_per_page -1;
				listGuestbook = GuestBookDao.messagelist(conn, firstRow, endRow);
				if(totalCount % message_per_page == 0) {
					totalPage = totalCount / message_per_page;
				} else {
				totalPage = totalCount / message_per_page + 1;
				}
			}else {
				currentPagenumber = 0;
				listGuestbook = Collections.emptyList();
			}
			listReq.setEndRow(endRow);
			listReq.setFirstRow(firstRow);
			listReq.setMESSAGE_PER_PAGE(message_per_page);
			listReq.setCurrentPageNumber(currentPagenumber);
			
			return listGuestbook;
		}catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
	}
	
	}
	public int totalPage() {
		return totalPage;
}
}
	
	


