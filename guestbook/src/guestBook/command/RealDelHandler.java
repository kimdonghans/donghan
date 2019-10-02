package guestBook.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestBook.service.DuplicateIdException;
import guestBook.service.GuestBookRequest;
import guestBook.service.RealDelService;
import guestBook.service.WriteService;
import mvc.command.CommandHandler;


public class RealDelHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/delfail.jsp";
	private RealDelService realdelService = new RealDelService();


	public String process(HttpServletRequest req, HttpServletResponse res) {
		RealDelService service = new RealDelService();
		GuestBookRequest delReq = new GuestBookRequest();

		String input_password = req.getParameter("password");
		
		delReq.setId(req.getParameter("id"));
		delReq.setPassword(req.getParameter("password"));
		
		System.out.println(input_password + req.getParameter("id")+req.getParameter("password"));
		
		try {
			if(realdelService.delcheck(delReq)) {
				return "/view/delsuccess.jsp";
			}else {
				return "/view/delfail.jsp";
			}

			
		} catch (DuplicateIdException e) {
//			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;

		}
	}
}
