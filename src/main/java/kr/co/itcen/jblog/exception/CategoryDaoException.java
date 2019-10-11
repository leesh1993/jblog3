package kr.co.itcen.jblog.exception;

public class CategoryDaoException extends RuntimeException{
	public CategoryDaoException() {
		super("CategoryDaoException Occurs");
		
	}
	
	public CategoryDaoException(String message) {
		super(message);
		
	}
}
