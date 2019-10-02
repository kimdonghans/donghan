package guestBook.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestBook.service.DuplicateIdException;
import guestBook.service.GuestBookRequest;
import guestBook.service.WriteService;
import mvc.command.CommandHandler;

public class WriteHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/writesuccess.jsp";
	private WriteService writeService = new WriteService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {

		WriteService service = new WriteService();	
		GuestBookRequest writeReq = new GuestBookRequest();
		
		writeReq.setName(req.getParameter("name"));
		writeReq.setPassword(req.getParameter("password"));
		writeReq.setMessage(req.getParameter("message"));

//		Map<String, Boolean> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
//
//		writeReq.validate(errors);
//System.out.println("Sss");
//		if (!errors.isEmpty()) {
//		return FORM_VIEW;
//		}

		try {
			service.write(writeReq);
			System.out.println("ddd");
			return "/view/writesuccess.jsp";
//			return FORM_VIEW;
		} catch (DuplicateIdException e) {
//			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
		

//	private String processForm(HttpServletRequest req, HttpServletResponse res) {
//		return FORM_VIEW;
//	}
//
//	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
//		
//	}
}
