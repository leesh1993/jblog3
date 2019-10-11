package kr.co.itcen.jblog.exception;

public class BlogDaoException extends RuntimeException{
	public BlogDaoException() {
		super("BlogDaoException Occurs");
		
	}
	
	public BlogDaoException(String message) {
		super(message);
		
	}
}
