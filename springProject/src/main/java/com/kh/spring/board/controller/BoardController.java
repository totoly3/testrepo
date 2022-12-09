package com.kh.spring.board.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.service.BoardServiceImpl;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//view 페이지 포워딩 
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="currentPage",defaultValue="1")int currentPage,
												Model model){
		
		//페이징처리를 위해 전체 게시글 개수 조회해오기 
		//페이징처리 pageLimit 10 
		//boardLimit 5   
		
		//페이징 처리 
		int listCount = boardService.selectListCount(); //총 게시글 개수  db에서 조회해오기 .
	
		int pageLimit = 10;	//하단에 페이징바 갯수
		int boardLimit =5; //한페이지에 몇개씩 띄울껀지!
	//		
		PageInfo pi=Pagination.getPageinfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		ArrayList<Board> list = boardService.selectList(pi);
		
		
		model.addAttribute("list",list);
		model.addAttribute("pi",pi);  //페이징바 처리해줄 
		
		return "board/boardListView";
	}
	
	//아래는 게시물 상세보기 
	@RequestMapping("detail.bo")
	public ModelAndView boardDetailView(int bno,ModelAndView mv,HttpSession session) {

		int result=boardService.increaseCount(bno);
	
		if(result>0) {
			Board b=boardService.selectBoard(bno);
			
//			mv.addObject("b",b);
//			mv.setViewName("board/boardDetailView");
//			session.setAttribute("alertMsg","조회성공");
			mv.addObject("b",b).setViewName("board/boardDetailView"); //한줄작성가능 
			
		}else {
			System.out.println("실패");
			mv.addObject("errorMsg","쉴패").setViewName("common/errorPage");
		}
		
		
		return mv;
		
	}
		
	//아래는 글쓰기 누르면 글작성 폼으로 이동 
	@GetMapping("insert.bo")
	public String insertBoard() {
		return "board/boardEnrollForm";
	}
	
	//글 등록 메소드 (사진등록 포함)
	@PostMapping("insert.bo")
	public ModelAndView insertBoard(Board b
									,MultipartFile upfile
									,ModelAndView mv
									,HttpSession session) {
		//전달된 파일이 있을경우 - 파일명 수정후에 서버로 업로드  - 원본명 ,서버에 업로드된 경로를 이어서 다운로드 처리 

		System.out.println(upfile.getOriginalFilename());  //비어(파일업로드하지않고 호출)있으면 비어있는 문자열이 생성됨
		
		if(!upfile.getOriginalFilename().equals("")) { //파일 업로드가 되었다면 ..!     빈문자열이랑 비교 ~
			
			//파일명 수정 후 서버로 업로드 
			//짱구 .jsp -> 2022120211231516.jsp 
			
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			
		/*
			//1.원본파일명 뽑기 
			String originName = upfile.getOriginalFilename();
			
			//2.시간 형식을 문자열로 뽑ㅁ기 
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			//3. 뒤에 붙일 랜덤값 ㅉ뽑기 
			int ranNum = (int)(Math.random()*90000+10000);
			
			//4.원본파일명으로부터확장자명 뽑아오기  
			String ext = originName.substring(originName.lastIndexOf(".")); //뒤에서 부터 오는 . 위치를 알려줘
	
			//5. 뽑아놓은 값 전부 붙여서 파일명 만들기 
			String changeName = currentTime + ranNum +ext;
			
			//6. 업로드 하고자 하는 실제 위치경로 지정해주기 
			String savePath =session.getServletContext().getRealPath("/resource/uploadFiles/");
			
			
			
			
			//7. 경로와 수정파일명 합쳐서 파일을 업로드해주기 
			try {
				upfile.transferTo(new File(savePath+changeName));
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		 */ // 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			// 여기 미리 작성해둔거 있음 아래에 
			
			
			String changeName = saveFile(upfile,session);
			//8. 원본명 , 서버에 업로드한 경로를 Board객체에 담아주기 
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/"+changeName);  
			//첨부파일이 없다면 -작성자,내용,제목 
			//첨부파일이 있다면 - 작성자,내용,제목,원본이름 ,저장경로
			int result = boardService.insertBoard(b);
			
			if(result>0) { //성공시 -게시판 리스트 띄워주기 (list.bo재요청)
				session.setAttribute("alertMsg","게시글 등록되었3");
				mv.setViewName("redirect:/list.bo");
			}else {//실패 -  에러페이지 로 포워딩 
				mv.addObject("errorMsg","게시글등록실패");
				mv.setViewName("common/errorPage");
			}
		}else {
			System.out.println("첨부없삼");
		}
		
		return mv;
		
	}
	//아래는 수정하기 폼으로 (글번호 가지고 이동)
	@RequestMapping("updateForm.bo")
	public String boardUpdateForm(int bno,Model model) {
		
		//아래는 수정하기 페이지에서 필요한 기존 게시글 정보 조회하여 보내주기  
		
		Board b = boardService.selectBoard(bno);
		
		model.addAttribute("b",b);
		return "board/boardUpdateForm";
	}
	
	
	//아래는 수정하기 폼에서 내용등을 변경 후 등록하기 누르면 변경됨 .  
	@RequestMapping("update.en")
	public ModelAndView boardUpdate(Board b,ModelAndView mv,HttpSession session
								,MultipartFile upfile) {
	
		if(!upfile.getOriginalFilename().equals("")) { //파일이 있는경우 
			//아래는 기존에 첨부파일이 있었던 경우  -> 기존첨부파일 삭제 
			if(b.getOriginName() != null) { //기존 첨부파일의 이름이 담겨있는 경우 
				new File(session.getServletContext().getRealPath(b.getChangeName())).delete();
			}
			// 새로운 첨부파일 업로드 
			String changeName=saveFile(upfile,session); //아래에서 작업한 saveFile메소드 사용
			
			// 데이터 DB에 등록 
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/"+changeName);
			
		}
		//위에서 업로드 세팅은 끝났으니 해당데이터를 service 에 전달 
		int result = boardService.updateBoard(b);
		System.out.println(result );
		if(result>0) {
			//상세보기 페이지로 재요청
			session.setAttribute("alertMsg","게시글 수정완");
			mv.setViewName("redirect:/list.bo?bno="+b.getBoardNo());
		}else {
			mv.addObject("errorMsg","게시글등록실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	//아래는 삭제하기 
	@RequestMapping("delete.bo")
	public ModelAndView boardDelete(int bno ,String filePath,ModelAndView mv,HttpSession session) {
		System.out.println(bno);
		int result=boardService.deleteBoard(bno);
		
		if(result>0) {//데이터 삭제에 성공했으면 - 서버에 저장된 파일도 삭제하여 자원을 낭비하지않는다
		System.out.println("성공하면 1:"+result);
			
			if(!filePath.equals("")) {//파일이 있는경우 
				new File(session.getServletContext().getRealPath(filePath)).delete();
				mv.setViewName("redirect:/list.bo");
			}
		}else {
			mv.addObject("errorMsg","게시글삭제실패");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}
	
	
	// 현재 넘어온 첨부파일 그 자체를 서버의 폴더에 저장시키는 메소드 (모듈)
		public String saveFile(MultipartFile upfile, HttpSession session) {
			// 1. 원본파일명 뽑기
			String originName = upfile.getOriginalFilename();
			// 2. 시간형식을 문자열로 뽑기
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			// 3. 뒤에 붙일 랜덤값 뽑기
			int ranNum = (int) (Math.random() * 90000 + 10000); // 5자리 랜덤값
			// 4. 원본 파일명으로부터 확장자명 뽑아오기
			String ext = originName.substring(originName.lastIndexOf("."));
			// 5. 뽑아놓은 값 전부 붙여서 파일명 만들기
			String changeName = currentTime + ranNum + ext;
			// 6. 업로드 하고자 하는 실제 위치 경로 지정해주기 (실제 경로. 8번 changeName이랑 비교)
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			// 7. 경로와 수정파일명 합쳐서 파일을 업로드해주기
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return changeName;
		}
		
		
		//	아래는 게시판 detail 뷰 댓글 전체조회 
		@ResponseBody
		@RequestMapping(value="rlist.bo",produces="application/json; charset=UTF-8")
		public String detailBoardReviewSelect(int bno, ModelAndView mv) {
			
	
			
			ArrayList<Reply> rlist=boardService.detailBoardReviewSelect(bno);
		
			return new Gson().toJson(rlist);
		}
		
		
		//아래는 댓글 등록 
		@ResponseBody
		@RequestMapping(value="rinsert.bo",produces="text/html; charset=UTF-8")
		public String insertReply(Reply r) {

			
			int result = boardService.insertReply(r);
//			if(result>0) {
//				
//			}else {
//				
//			}
			System.out.println("댓글등록 성공했으면1: "+result); 
			return result>0 ? "yes" : "no";
		}
		
		//아래는 조회수 top5  메인 jsp에 띄워ㅏ주기 
		@ResponseBody									//list형식이라서 json사용했음 . 
		@RequestMapping(value="topBoard.bo",produces="application/json; charset=UTF-8")
		public String topBoard() {
		System.out.println();
	
		ArrayList<Board> list = boardService.topBoard();
		return new Gson().toJson(list);
			
		}
}
