package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.PostVo;
@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getCategory(String id) throws UserDaoException {
		return sqlSession.selectOne("post.getPost",id);
	}
}
