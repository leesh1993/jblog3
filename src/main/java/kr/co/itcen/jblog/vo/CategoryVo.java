package kr.co.itcen.jblog.vo;

public class CategoryVo {
	private int no;
	private String name;
	private String explanation;
	private String wdate;
	private String bid;
	private String pcount; // 해당 카테고리의 포스트 수
	private String ccount; // 전체 카테고리 수(카테고리 add할 때 가져옴) 
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getPcount() {
		return pcount;
	}
	public void setPcount(String pcount) {
		this.pcount = pcount;
	}
	public String getCcount() {
		return ccount;
	}
	public void setCcount(String ccount) {
		this.ccount = ccount;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", explanation=" + explanation + ", wdate=" + wdate
				+ ", bid=" + bid + ", pcount=" + pcount + ", ccount=" + ccount + "]";
	}	

}
