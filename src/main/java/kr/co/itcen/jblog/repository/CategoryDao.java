package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;
@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;	
	
	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList("category.getCategory",id);
	}
	
	public CategoryVo getLastCategory(String id) {
		return sqlSession.selectOne("category.getLastCategory",id);
	}
	
	public int getCount(String id) {
		return sqlSession.selectOne("category.getCount",id);
	}
	
	public Boolean addCategory(String bid, String name, String explanation) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("bid", bid);
		map.put("name", name);
		map.put("explanation", explanation);
		
		
		int count = sqlSession.insert("category.categoryAdd",map);
		
		return count == 1;	
	}
	
	public Boolean deleteCategory(int no) {
		
		int count = sqlSession.update("category.deleteCategory", no);
		
		return count == 1;
	}
}
