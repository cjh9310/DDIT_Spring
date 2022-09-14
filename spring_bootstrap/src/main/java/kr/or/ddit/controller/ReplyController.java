package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;


//url : /replies

//bno번 게시글의 페이징 처리된 댓글 목록
// /replies/{bno}/{page} 			page list: GET방식
// /replies 						regist : POST 방식 : 댓글 입력
// /replies/{rno}					modify : PUT,PATCH방식, rno 댓글의 수정 
// /replies/{bno}/{rno}/{page}		remove : DELETE 방식, rno 댓글의 삭제
// /replies 

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	
	@Autowired
	private ReplyService service;
	

	
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("bno") int bno, 
														 @PathVariable("page") int page)
																throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Criteria cri = new Criteria();
		cri.setPage(page);
		Map<String, Object> dataMap = service.getReplyList(bno, cri);		
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);		
		return entity;
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply)throws Exception{
		
		ResponseEntity<String> entity = null;
		
		reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));
		
		service.registReply(reply);
		
		Criteria cri = new Criteria();
		
		Map<String, Object> dataMap = service.getReplyList(reply.getBno(), cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		int realEndPage = pageMaker.getRealEndPage();
		
		entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);
		
		return entity;
		
	}
	
	
	@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@PathVariable("rno") int rno,
										 @RequestBody ReplyVO reply) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		reply.setRno(rno);
		
		service.modifyReply(reply);
		entity = new ResponseEntity<String>(HttpStatus.OK);
		
		return entity;
	}
	
	
	@RequestMapping(value = "/{bno}/{rno}/{page}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno")  int rno,
										 @PathVariable("bno")  int bno,
										 @PathVariable("page")  int page) throws Exception{
		
		ResponseEntity<String> entity = null;
		service.removeReply(rno);
		Criteria cri = new Criteria();
		Map<String, Object> dataMap = service.getReplyList(bno, cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		
		int realEndPage = pageMaker.getRealEndPage();
		if(page > realEndPage) {
			page = realEndPage;
		}
		entity = new ResponseEntity<String>("" + page, HttpStatus.OK);
		return entity;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
