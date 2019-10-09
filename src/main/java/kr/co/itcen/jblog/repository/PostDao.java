package kr.co.itcen.jblog.repository;

import java.util.HashMap;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;
@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String title, int cno, String contents){
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("title", title);
		map.put("cno", cno);
		map.put("contents", contents);
		
		
		int count = sqlSession.insert("post.insert",map);
		return count == 1;	
	}
	
	public List<PostVo> getPostList(int cno){
	
		return sqlSession.selectList("post.getPostList", cno);
	}
	
	public PostVo getSelectedPost(int cno,int pno){
		HashMap<String, Integer> map = new HashMap<String, Integer>();		

		map.put("cno", cno);
		map.put("pno", pno);
		
		return sqlSession.selectOne("post.getSelectedPost", map);
	}
}
