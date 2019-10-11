package kr.co.itcen.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.FileUploadVo;

@Service
public class BlogService {

	private static final String SAVE_PATH = "C:/Users/BIT/Desktop/java/eclipse-workspace/jblog3/src/main/webapp/assets/images/logo/";
	private static final String URL_PREFIX = "/images/logo/";

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private PostDao postDao;

	public BlogVo getBlog(String id) throws UserDaoException {
		return blogDao.getBlog(id);
	}

	public List<CategoryVo> getCategory(String id) {
		return categoryDao.getCategory(id);
	}
	
	public CategoryVo getLastCategory(String id) {
		return categoryDao.getLastCategory(id);
	}
	
	public int getCount(String id) {
		return categoryDao.getCount(id);
	}

	public List<PostVo> getPostList(int cno) {
		return postDao.getPostList(cno);
	}
	

	public PostVo getSelectedPost(int cno, int pno) {
		return postDao.getSelectedPost(cno, pno);
	}

	public boolean insert(String title, int cno, String contents) {

		return postDao.insert(title, cno, contents);
	}
	
	public boolean blogUpdate( String uid, String title) {

		return blogDao.blogUpdate(uid, title);
	}
	
	public Boolean addCategory(String bid, String name, String explanation) {
		
		return categoryDao.addCategory(bid, name, explanation);
	}
	
	public Boolean deleteCategory(int no) {
	
		return categoryDao.deleteCategory(no);
	}

	
	public String restore(String bid, MultipartFile multipartFile) {

		String url = "";

		if (multipartFile == null) {
			return url;
		}

		String originalFilename = multipartFile.getOriginalFilename();
		String saveFileName = generateSaveFilename(originalFilename.substring(originalFilename.lastIndexOf('.') + 1));
		long fileSize = multipartFile.getSize();
		
		byte[] fileData;
		try {
			fileData = multipartFile.getBytes();
			File file = new File(SAVE_PATH + saveFileName);
			
			OutputStream os = new FileOutputStream(file);
			os.write(fileData);
			os.close();

			url = URL_PREFIX + saveFileName;

			FileUploadVo vo = new FileUploadVo();

			vo.setOriginal_name(originalFilename);
			vo.setSave_name(saveFileName);
			vo.setBid(bid);

			blogDao.upload(vo);
			
			blogDao.logoUpdate(bid, url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public FileUploadVo getFile(String bid) {

		return blogDao.getFile(bid);
	}
}
