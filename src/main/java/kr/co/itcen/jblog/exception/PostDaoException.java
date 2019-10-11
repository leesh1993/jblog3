package kr.co.itcen.jblog.exception;

public class PostDaoException extends RuntimeException {
	public PostDaoException() {
		super("PostDaoException Occurs");
		
	}
	
	public PostDaoException(String message) {
		super(message);
		
	}
}
