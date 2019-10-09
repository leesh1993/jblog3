package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;


@Controller
@RequestMapping( "/{id:(?!assets).*}" )
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index(@PathVariable String id,
						@PathVariable Optional<Integer> pathNo1,
						@PathVariable Optional<Integer> pathNo2, 
						Model model) {
		
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);

		List<CategoryVo> categoryList  = blogService.getCategory(id);	
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
	
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable String id) {
		
		System.out.println("id : " +id);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable String id) {
		
		System.out.println("id : " +id);
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable String id,
			                 Model model) {
		
		List<CategoryVo> categoryList  = blogService.getCategory(id);	
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/admin/postWrite", method = RequestMethod.POST )
	public String postWrite(@PathVariable String id,
							@RequestParam("title") String title,
							@RequestParam("category") int category, 
							@RequestParam("contents") String contents) {
		
		System.out.println("title : " + title + "  category : " + category + "  contents : " + contents);
		
		blogService.insert(title, category, contents);
		
		return "redirect:/" + id;
	}
}
