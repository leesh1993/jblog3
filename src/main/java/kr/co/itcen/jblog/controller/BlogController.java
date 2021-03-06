package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;



@Controller
@RequestMapping( "/{id:(?!assets).*}" )
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index(@PathVariable String id,
						@PathVariable Optional<Integer> pathNo1,
						@PathVariable Optional<Integer> pathNo2, 
						HttpSession session,
						Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			if(id.equals(authUser.getId())) {
				model.addAttribute("user", "user");
			}		
		}
		model.addAttribute("admin", "main");
		
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		List<CategoryVo> categoryList  = blogService.getCategory(id);	
		
		if(categoryList.size() != 0) {
			model.addAttribute("categoryList", categoryList);
			int categoryNo = categoryList.get(0).getNo();
			int postNo = 1;
			
			if( pathNo2.isPresent() ) {
				postNo = pathNo2.get();
				categoryNo = pathNo1.get();
			} else if( pathNo1.isPresent() ){
				categoryNo = pathNo1.get();
			}
			
			List<PostVo> postlist  = blogService.getPostList(categoryNo);
			model.addAttribute("postlist", postlist);
			
			PostVo selectedPost  = blogService.getSelectedPost(categoryNo,postNo);	
			model.addAttribute("selectedPost", selectedPost);	
		}
		
	
		return "blog/blog-main";
	}
	
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable String id,
					         HttpSession session,
			                 Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		if(authUser != null) {		
			if(!id.equals(authUser.getId())) {
				return "redirect:/" + id;
			}
			model.addAttribute("user", "main");
		}else {
			return "redirect:/" + id;
		}
		
		
		/*블로그 정보 가져오기*/
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/admin/basic/changedBlog", method = RequestMethod.POST)
	public String changedBlog(@PathVariable String id,
							  @RequestParam("title") String title,
							  @RequestParam(value="file-logo",required=false) MultipartFile multipartFile,
							  HttpSession session,
			                  Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {		
			if(!id.equals(authUser.getId())) {
				return "redirect:/" + id;
			}
			model.addAttribute("user", "main");
		}else {
			return "redirect:/" + id;
		}
		

		blogService.blogUpdate(id, title);

		if(multipartFile.getOriginalFilename().lastIndexOf('.') != -1) {
			blogService.restore(id, multipartFile);

		}else {
			System.out.println("null");
		}
		
		return "redirect:/" + id;
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable String id,
							    HttpSession session,
			                    Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {		
			if(!id.equals(authUser.getId())) {
				return "redirect:/" + id;
			}
			model.addAttribute("user", "main");
		}else {
			return "redirect:/" + id;
		}
		
		/*블로그 정보 가져오기*/
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		
		/*카테고리 정보 가져오기*/
		List<CategoryVo> categoryList  = blogService.getCategory(id);	
		model.addAttribute("categoryList", categoryList);

		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable String id,
							 HttpSession session,
			                 Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {		
			if(!id.equals(authUser.getId())) {
				return "redirect:/" + id;
			}
			model.addAttribute("user", "main");
		}else {
			return "redirect:/" + id;
		}
		
		/*블로그 정보 가져오기*/
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		
		List<CategoryVo> categoryList  = blogService.getCategory(id);	
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/admin/postWrite", method = RequestMethod.POST )
	public String postWrite(@PathVariable String id,
							HttpSession session,
							@RequestParam("title") String title,
							@RequestParam("category") int category, 
							@RequestParam("contents") String contents) {
	
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			if(!id.equals(authUser.getId())) {
				return "redirect:/" + id;
			}
		}
		
		
		blogService.insert(title, category, contents);
		
		return "redirect:/" + id;
	}
}
