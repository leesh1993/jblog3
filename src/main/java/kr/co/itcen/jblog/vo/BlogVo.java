package kr.co.itcen.jblog.vo;

public class BlogVo {

	private String uid;
	private String title;
	private String logo;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString() {
		return "BlogVo [uid=" + uid + ", title=" + title + ", logo=" + logo + "]";
	}
	
}
