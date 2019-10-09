package kr.co.itcen.jblog.vo;

public class PostVo {

	private int no;
	private String title;
	private String text;
	private String wdate;
	private int cno;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", text=" + text + ", wdate=" + wdate + ", cno=" + cno + "]";
	}
	
	
}
