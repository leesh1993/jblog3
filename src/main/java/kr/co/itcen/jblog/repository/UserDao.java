package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;		
	
	public Boolean insert(UserVo vo) throws UserDaoException {
		int count = sqlSession.insert("user.insert",vo);
		sqlSession.insert("blog.insert",vo);
		sqlSession.insert("category.defaultInsert",vo);
		
		return count == 1;	
	}
	
	public UserVo get(UserVo vo) throws UserDaoException {
		return sqlSession.selectOne("user.getByIdAndPassword",vo);
	}
	

	public UserVo get(String id) throws UserDaoException {
		
		return sqlSession.selectOne("user.getById", id);	
	}



	
}
