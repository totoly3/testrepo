package com.kh.spring.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

//아래는 이걸 컨트롤러로 ㅣ용할꺼야 
@Controller //Controller 타입의 어노테이션을 붙여주면 빈 스캐닝을 통해 자동으로 bean을 등록한다 . 

public class MemberController {
	
	/*기존방법
	 * private MemberService memberService = new MemberServiceImpl();
	 * 
	 * 기존 객체 생성 방식은 객체 간의 결합도가 높아진다(소스코드의 수정이 일어날경우 하나하나 다 바꿔야하는 단점이 있음)
	 * 서비스가 동시에 많은 횟수가 요청될 경우 그만큼 많은 객체가 생성된다.
	 * 
	 * 
	 * ----spring------=-
	 * Spring의 DI (Dependency Injection) 을 이용한 방식으로 객체를 생성화여 주입한다 (객체간의 결합도를 낮춤)
	 * new 라는 키워드 없이 선언문만 써줘도 되지마 @Autowired라는 이노테이션을 *필수로 작성해야함. 
	 * */
//	@Autowired
//	private MemberService memberService;
	
	
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;
//	@RequestMapping(value="login.me")
//	public void loginMember() {
//		System.out.println("들어왔나요?");
//	}
	
	/*
	 * @RequestMapping(value="매핑값") - Request타입의 어노테이션을 붙여줌으로서 HandLerMappering을 등록한다 . 
	 * *Spring 에서 파라미터(요청시 전달값) 을 받는 방법 
	 * 
	 * 1.HttpServletRequest 를 이용하여 전달받기 (기존의 jsp/servlet 때의 방식) 
	 * 	해당 메소드의 매개변수로 HttpServletRequest 를 작성해두면 
	 * 	스프링 컨테이너가 해당 메소드를 호출시 (실행시) 자동으로 해당객체를 생성해서 매개변수로 주입해준다. 
	 * 
	 * */
	//아래 value 생략가능
/*	@RequestMapping("login.me")
	public String loginMember(HttpServletRequest request) {
			String userId = request.getParameter("userId");
			String userPwd = request.getParameter("userPwd");

//			아래는 forward
//			return "main";
//			아래는 redirect
			return"redirect:/";
	}
	*/

	/*
	 * 2번    @RequestParam 어노테이션을 이용하는 방법  
	 * 		request.getParameter("키") 로 벨류를 뽑아오는 역할을 하는 매개변수로 담아올수 ㅇ있따 .
	 * 		-만약 , 넘어온 값이 비어잉ㅆ는 형태라면 defaultValue 속성으로 기본값을 설정해줄수 있다 . 
	 * */
	/*
	@RequestMapping("login.me")
	public String loginMember(@RequestParam(value="userId")String userId,
							  @RequestParam(value="userPwd")String userPwd,
							  @RequestParam(value="email",defaultValue="sss")String email){
		System.out.println("리퀘스트파람을 이용한 값 :"+userId);
		System.out.println("리퀘스트파람을 이용한 값 :"+userPwd);
		System.out.println(email);
		
		return "main" ;
			
	}
	*/
	/*
	 * 3. @RequestParam 어노테이션 생략하는 방법 
	 * 	-단 매개변수명을 jsp name 속성값 (요청시 전달한 키값 )과 동일하게 작성하여야 자동 주입이 된다 . 
	 * 	-또한 위에서 사용했던 defaultValue같은 추가 속성은 작성할수 없다 . 
	 * 	 * 	 * */
	
	/*
		@RequestMapping("login.me")
		public String loginMember(String userId,String userPwd,String email
				,@RequestParam(value="userId")String userId2) {
			System.out.println("파람 생략한 방법: "+userId);
			System.out.println("파람 생략한 방법: "+userPwd);

			System.out.println("파람 생략한 방법: "+email); //없으면 null반환 
			System.out.println("파람 생략한 방법: "+userId2); //없으면 null반환 
			
			return "redirect:/";
		}
	
	*/
	
	/*
	 * 4번 !    커멘드 객체 방식 
	 * - 해당 메소드의 매개변수로 요청시 전달값을 담고자하느 vo 클래스 타입을 세팅하여 
	 * -요청시 전닭밧의 킥밧 (jsp name ) 을 VO 클래스에 담고자하는 필드명으로 작성한다 .
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성하여 내부적으로 setter 메소드를 찾아 요청시 전달값을 해당 필드에 담아주는 원리
	 * 
	 * 
	 * 이또한 마찬가지로 반드시 name 명이 일치하여야 한다 .  *
	 * 
	 * 
	 * --아래는  요청처리 후 응답페이지로 응답데티러르 가지고 포워딩 또느 url 재요청하는 방법 
	 * 1.스프링에서 제공하는 Model 객체를 이용하는 방법 
	 * 
	 * - 포워딩할 응답뷰로 전달하고자 하는 데이터를 맵 형식(key - value) 로 담을수 있는 영역 
	 * -Model 객체는 requestScope이다  , 단 , setAttribute 가 아닌 addAttribute 메소드를 이용하여야 한다
	 *  
	 *  */
	
	
	
	//아래는 로그인
	
	@Autowired
	private MemberService memberService;
	
	
	//아래는 비밀번호 암호화 전 로그인   , 암호화후 로그인으로 하는게 좋아서 아래에 또있음
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m,HttpSession session,Model model ) {
//		System.out.println("VO클래스로 전달받기"+m.getUserId());
//		System.out.println("VO클래스로 전달받기"+m.getUserPwd());
	
		Member loginUser =memberService.loginMember(m);
		
		if(loginUser==null) {//로그인 실패 -requestScope에 에러메세지로 포워딩
			model.addAttribute("errorMsg","로긴 실패");  //옜날엔 request.setAttribute("errorMsg","로그인실패")
			return "common/errorPage"; //이미 WEB-INF/views 와 뒤에jsp 는 이미 servlet context에 붙어있음
		}else {//로그인성공 loginUser를 SessionScope 에 담아서 메인 페이지로 재요청
			session.setAttribute("loginUser",loginUser );
	
			//url 재요청 방식 -sendRedirect 방식 (url 주소를 제시하여 재요청)
			//redirect : 요청 url 
			return "redirect:/"; //contextPath 뒤에 붙는 /를 의미하여 메인페이지(index)
	
		}
	}
	*/
	
	//비밀번호 암호화 후 로그인 처리 
	/*
	 * 2. 스프링에서 제공하는 ModelAndView 클래스 
	 * model 은 데이터를 key-value 세트로 담을수있는 공간 
	 * view  는 응답뷰에 대한 정보를 담을 수있는 공간 
	 * 이 경우에는  반환타입이 String 이 아니라 ModelAndView 형태로 반환하여야 한다. 
	 * Model과 View 가 결합된 형태 단 , Model은 위에서 사용했듯이  단독사용이 가능하ㅈ만 . 
	 * View 는 단독사용을 할수 없다 . 
	 * */
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m
							 ,HttpSession session
							 ,ModelAndView mv) {
		Member loginUser = memberService.loginMember(m);
		//loginUser : 아이디만으로 조회해온 회원정보
		//loginUser의 userPwd 필드에는 암호화되어서 DB에 저장된 암호비밀번호가 들어있다.
		//그 암호화된 비밀번호와 사용자가 입력한 비밀번호가 암호화되었을시에 일치하게 되는지
		//확인해주는 메소드를 사용하여 해당 정보가 일치하는지 구분한다.
		//이때 사용하는 메소드는 BCryptPasswordEncoder 객체의 matches 메소드이다.
		//matches(평문,암호문)을 작성하면 내부적으로 복호화 작업이 이루어져
		//두 데이터가 일치하는지 확인하여 true/false로 반환한다.
		//사용자가 입력한 로그인폼에서 넘어온 비밀번호 : m.getUserPwd();
		//데이터베이스에서 조회해온 암호화된 비밀번호 : loginUser.getUserPwd();
		
		System.out.println("넘겨받은 비밀번호 (원본) : "+m.getUserPwd());
		System.out.println(BCryptPasswordEncoder.encode(m.getUserPwd()));
		if(loginUser != null && BCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			
			session.setAttribute("loginUser", loginUser);
			//setViewName : 요청 주소
			mv.setViewName("redirect:/"); //메인화면 재요청
			
			System.out.println("넘겨받은 비밀번호 (원본) : "+m.getUserPwd());
			System.out.println("DB조회 비밀번호 (암호화) : "+loginUser.getUserPwd());
			System.out.println("성공");
			
		}else { //실패
			//addObject : setAttribute나 addAttribute와 같은 용도(requestScope에 등록)
			session.setAttribute("alertMsg","로그인 실패");
			mv.addObject("errorMsg", "로그인 실패!!!!!!");
			mv.setViewName("common/errorPage");
			System.out.println("로그실패");
		}
		return mv;
	}
	
	
	
	
	//아래는 로그아웃 	
	@RequestMapping("logout.me")
	public String logOutMember(HttpSession session){
		//	session.invalidate();  둘중하나 쓰면됨   
		session.removeAttribute("loginUser");
		return "redirect:/";
		
	}
	
	
	//아래는 회원가입 폼으로 이동 
	@GetMapping("insert.me")
	public String enrollForm() {
		
		//원래 경로라면 WEB-INF/views/member/membeEnrollForm.jsp
		return "member/memberEnrollForm";
	}
	
	
	
	//회원가입 등록
		@PostMapping("insert.me")
		public String insertMember(Member m, HttpSession session, Model model) {
			
			// 1. 한글깨짐 - web.xml에서 encodingFilter를 통해 해결
			// 2. 나이에 빈값이 들어오면 typeMismatchException 발생
			//	 -Member VO에 age필드를 String자료형으로 변경하여 해결 (lombok활용)
			// 3. 비밀번호가 사용자가 입력한 그대로 저장되는 문제
			// -Bcypt 방식의 암호화를 통해 암호문으로 변경
			// -spring security module에서 제공하는 라이브러리
			// -BcryptPasswordEncoding 클래스를 사용하기 위해 xml파일에 등록
			// -web.xml에 spring-security.xml 파일을 로딩할 수 있게 작업
			
			System.out.println("원본 패스워드 : "+m.getUserPwd());
			String encPwd = BCryptPasswordEncoder.encode(m.getUserPwd());
			System.out.println("암호화 패스워드 : "+encPwd);
			//$2a$10$6VFdMHndTYpRQpRppd04SOzqPMm8zty4Qua/CWV19Sroq9pHr/Bja
			
			m.setUserPwd(encPwd);
			
			int result = memberService.insertMember(m);
			
			if(result != 0) {
				session.setAttribute("alertMsg", "회원가입에 성공했습니다!");
				return "redirect:/";
			}else {
				model.addAttribute("errorMsg", "회원가입실패!!");
				return "common/errorPage";
			}
			
		}
	
	//마이페이지로 이동
	@RequestMapping("mypage.me")
	public String myPage() {
		return "member/mypage";
	}
	
	//마이페이지에서 수정 
	@RequestMapping("update.me")
	public String update(Member m,HttpSession session,Model model,ModelAndView mv) {
		System.out.println(m);
	//성공시 session 에 있던 loginUser 를 지우고  변경된 loginUser 를 넣어주고 
	//마이페이지로 재요청 보내기 (alertMsg 보내보기 ) -
	//  실패시 에러페이지에 에러메세지 담아서 보내기 
		
		int result=memberService.updateMember(m);
		System.out.println(result);
	
	if(result>0) { //성공시 loginUser 지우고 변경된 loginuser 넣기 
		System.out.println(result);
		System.out.println("수정성공?");
		
		Member updateMember = memberService.loginMember(m);
		session.setAttribute("loginUser", updateMember);
		session.setAttribute("alertMsg","수정완");
		return "redirect:/mypage.me";
		
	
	}else {
		System.out.println("여긴오나?"+result);
		model.addAttribute("errorMsg", "수 ㅣㄹ패");
	
		return "common/errorPage";
	}
	
	}
	
	//아래는 회원탈퇴 
	@RequestMapping("delete.me")//매치스 활용해서 회원탈퇴 
	public ModelAndView delete(String userPwd,HttpSession session,HttpServletRequest request,ModelAndView mv) {
		
		System.out.println(userPwd);
		
		//로그인 되어있느 ㄴ회원정보의 비밀번호(암호화되어있는) 를 
		//사용자에게 전달 받은 비밀번호(평문)과 일치하는지 matches 메소드를 이용하여 확인 
		Member loginUser =(Member)session.getAttribute("loginUser"); //로그인 회원ㅌ정보 
		String userId = loginUser.getUserId(); //로그인 정보 아이디 
		String loginuserPwd = loginUser.getUserPwd(); //로그인 정보 비밀번호 
		

		//만약에 비밀번호가 같거나 null이 아니라면 							"넘겨받은 비밀번호 (원본)+m.getUserPwd
		if( BCryptPasswordEncoder.matches(userPwd, loginuserPwd)) {//입력 받은 비밀번호와 암호화된 비밀번호가 일치하다면 123 과 $!@12fdasf 값이 일치하냐 
			//탈퇴 처리후 로그인유저 정보 삭제 - 메인페이지 보내기 -알림메세지 추가 
				
		int result = memberService.deleteMember(userId);
		
			if(result>0) {
				//최종적으로 탈퇴성공
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg","그동안 고마워따 다신보지말자" );
				mv.setViewName("redirect:/");
			}else {
				//탈퇴 처리 실패 
				mv.addObject("errorMsg","탈퇴 실푀!");
				mv.setViewName("common/errorPage");
			}
			
		}else {
			//여기는 비밀번호가 일치하지 않을때 
			//탈퇴실패시 메세지 보내고 마이페이지 재요청 
			session.setAttribute("alertMsg","너 비번틀렸음");
			mv.setViewName("redirect:/");
		}
		return mv;

	}
	@ResponseBody
	@RequestMapping(value="idCheck.me")
	public String idcheck(String idcheck) {
		System.out.println(idcheck);
		int result=memberService.idcheck(idcheck);
		System.out.println(result);
		if(result==0) { //조회된 회원이 없음(아이디 사용가능) 0 
			return "NNNNY";
		}else {  //사용불가  일치하는게있으면 1 
			return "NNNNN";
		}
		
//		return (m!=null)?"NNNNN" :"NNNNY";
	}
	
	
	
}
