package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.CategoryVo;
@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;	
	
	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList("category.getCategory",id);
	}
}
