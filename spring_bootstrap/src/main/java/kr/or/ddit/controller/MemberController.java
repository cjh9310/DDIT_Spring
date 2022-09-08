package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsp.command.Criteria;
import com.jsp.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("list")
	public String list(Criteria cri,HttpServletRequest request) throws Exception{
		String url = "member/list";
		
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		
		request.setAttribute("dataMap", dataMap);
		
		return url;
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		String url = "member/regist";
		return url;	
	}
	
	
	
}





