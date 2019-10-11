package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.CategoryVo;

@Controller("blogApiController")
@RequestMapping("/api/blog")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@ResponseBody
	@RequestMapping("/category/add")
	public String addCategory(@RequestBody CategoryVo categoryVo) {
		
		blogService.addCategory(categoryVo.getBid(),categoryVo.getName(),categoryVo.getExplanation());
		CategoryVo vo = blogService.getLastCategory(categoryVo.getBid());
		
		String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
        	str = mapper.writeValueAsString(vo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return str;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult deleteCategory(@RequestParam(value = "no", required = false) int no) {
		
		
		System.out.println("no : " + no);
		boolean result = blogService.deleteCategory(no);
		
		return JSONResult.success(result);

	}
}
