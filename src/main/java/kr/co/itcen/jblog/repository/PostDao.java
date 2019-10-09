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
