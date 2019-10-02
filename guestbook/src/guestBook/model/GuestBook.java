package guestBook.model;

import java.util.Date;
import java.util.List;

public class GuestBook {

	private String id;
	private String name;
	private String password;
	private String message;


	public GuestBook(String id, String name, String password, String message) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.message = message;

	}

	public GuestBook(String id) {
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public String getMessage() {
		return message;
	}

}
