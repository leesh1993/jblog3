package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;


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
			
		int categoryNo = 1;
		int postNo = 1;
		
		if( pathNo1.isPresent() ) {
			categoryNo = pathNo1.get();
		} else if( pathNo2.isPresent() ){
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}
	
		System.out.println("id : " + id);
		System.out.println("categoryNo : " + categoryNo);
		System.out.println("postNo : " + postNo);
		
		BlogVo blog = blogService.getBlog(id);
		List<CategoryVo> list  = blogService.getCategory(id);
		
		model.addAttribute("blog", blog);
		model.addAttribute("category", list);
		
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
	public String adminWrite(@PathVariable String id) {
		
		System.out.println("id : " +id);
		
		return "blog/blog-admin-write";
	}
}
