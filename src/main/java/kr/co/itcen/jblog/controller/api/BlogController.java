package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.BlogService;

@Controller("blogApiController")
@RequestMapping("/api/blog")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@ResponseBody
	@RequestMapping("/add")
	public int addCategory(@RequestParam(value = "bid", required = false)String bid,
			                    @RequestParam(value = "name", required = false)String name,
			                    @RequestParam(value = "explanation", required = false)String explanation) {
			
		System.out.println("bid : "+ bid + " name : "+ name + " explanation : " + explanation);
		blogService.addCategory(bid,name,explanation);
		
		int getCount = blogService.getCount(bid);
		
		return getCount;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult deleteCategory(@RequestParam(value = "no", required = false) int no) {
		
		
		System.out.println("no : " + no);
		boolean result = blogService.deleteCategory(no);
		
		return JSONResult.success(result);

	}
}
