package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.FileUploadVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;	
	
	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlog",id);
		
	}
	
	public boolean blogUpdate( String uid, String title) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("uid", uid);
		map.put("title", title);
		
		int count = sqlSession.update("blog.blogUpdate", map);
		
		return count == 1;	
	}

	public boolean upload(FileUploadVo vo) {
		int count = sqlSession.insert("blog.upload",vo);
		
		return count == 1;	
	}

	public boolean logoUpdate(String uid, String logo) {
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("uid", uid);
		map.put("logo", logo);
		
		int count = sqlSession.update("blog.logoUpdate",map);
		
		return count == 1;	
	}
	
	public FileUploadVo getFile(String bid) {
		
		return sqlSession.selectOne("blog.getFile",bid);
	}
	

}
