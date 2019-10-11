package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.itcen.jblog.exception.CategoryDaoException;
import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;	
	
	public List<CategoryVo> getCategory(String id) throws CategoryDaoException{
		return sqlSession.selectList("category.getCategory",id);
	}
	
	public CategoryVo getLastCategory(String id)  throws CategoryDaoException{
		return sqlSession.selectOne("category.getLastCategory",id);
	}
	
	public int getCount(String id)  throws CategoryDaoException{
		return sqlSession.selectOne("category.getCount",id);
	}
	
	public Boolean addCategory(String bid, String name, String explanation) throws CategoryDaoException{
		
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("bid", bid);
		map.put("name", name);
		map.put("explanation", explanation);
		
		
		int count = sqlSession.insert("category.categoryAdd",map);
		
		return count == 1;	
	}
	
	public Boolean deleteCategory(int no) throws CategoryDaoException{
		
		int count = sqlSession.update("category.deleteCategory", no);
		
		return count == 1;
	}
}
