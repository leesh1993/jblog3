package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	

	
	public BlogVo getBlog(String id) throws UserDaoException {
		return blogDao.getBlog(id);
	}
	
	public List<CategoryVo> getCategory(String id){
		return categoryDao.getCategory(id);
	}
	
}
