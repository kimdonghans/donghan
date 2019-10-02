package guestBook.command;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestBook.service.GuestBookRequest;
import guestBook.service.WriteService;
import guestBook.service.DuplicateIdException;
import guestBook.service.GuestBookRequest;
import guestBook.service.WriteService;
import mvc.command.CommandHandler;

public class DelHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/del.jsp";


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		GuestBookRequest delReq = new GuestBookRequest();
		delReq.setName(req.getParameter("messageId"));
		delReq.setPassword(req.getParameter("messagepassword"));
		System.out.println(req.getParameter("messageId")+req.getParameter("messagepassword"));
		
		return FORM_VIEW;
	}
}
