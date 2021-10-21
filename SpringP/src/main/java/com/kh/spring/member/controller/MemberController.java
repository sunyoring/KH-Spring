package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser") // Model에 loginUser라는 키값으로 객체가 추가되면 자동으로 세션에 추가해주는 어노테이션
@Controller // bean스캐닝을 통해 자동으로 Controller타입으로 등록 됨
public class MemberController {
	
	
	
	/*error-page
	 * exception-type
	 * ExceptionHandler
	 * ControllerAdVICE + ExceptionHandler
	 * */
	
	/*
	 * @ExceptionHandler(value=BadSqlGrammarException.class) public ModelAndView
	 * controllerExceptionHandler(Exception e) { e.printStackTrace(); return new
	 * ModelAndView("common/errorPageServer").addObject("msg",e.getMessage()); }
	 */
	
	/*
	 * @ExceptionHandler(value=BadSqlGrammarException.class) public ModelAndView
	 * controllerExceptionHandler(Exception e) { e.printStackTrace(); return new
	 * ModelAndView("common/errorPage").addObject("msg",e.getMessage()); }
	 */

	@Autowired // 빈스캐닝을 통해서 인터페이스를 구현한 구현체를 찾아 등록시켜줌 (구현체에는 @Service 어노테이션이 등록되어 있어야 함)
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 파라미터를 전송 받는 방법
	/*
	 * 1. HttpServletRequest를 통해 전송 받기 (기존 jsp/servlet 방식 )
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST)
	 * //@RequestMapping 을 붙여줌으로써 handlerMapping에 등록 public String
	 * loginMember(HttpServletRequest request) {
	 * 
	 * String userId = request.getParameter("userId"); String userPwd =
	 * request.getParameter("userPwd");
	 * 
	 * System.out.println(userId +" : "+ userPwd);
	 * 
	 * return "main"; //리턴되는 문자열을 servlet-context의 viewResolver에 의해 사용자가 보게 딜 뷰로 포워딩
	 * }
	 */

	/*
	 * 2. @RequestParam 어노테이션 방식 - 스프링에서 제공하는 파라미터를 받아오는 방식
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public String
	 * loginMember(@RequestParam("userId") String userId,
	 * 
	 * @RequestParam("userPwd") String userPwd) {
	 * 
	 * 
	 * System.out.println(userId +" : "+ userPwd);
	 * 
	 * return "main"; }
	 * 
	 */

	/*
	 * 3. @RequestParam 어노테이션 생략 - 매개 변수 name을 동일하게 작성해야 자동으로 값이 주입됨
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public String
	 * loginMember(String userId, String userPwd1) {
	 * 
	 * 
	 * System.out.println(userId); System.out.println(userPwd1);
	 * 
	 * return "main"; }
	 */

	/*
	 * 4. @ModelAttribute 이용하는 방법 - 요청 파라미터가 많은 경우 객체타입으로 넘겨받는데 기본 생성자와 setter를 이용한
	 * 주입방식이므로 둘 다 존재해야 하고* - 반드시 name 속성에 있는 필드명이 동일 해야하며 setter 작성규칙에 맞게 작성되어야 한다.
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public String
	 * loginMember(@ModelAttribute Member m) {
	 * 
	 * System.out.println(m.getUserId()); System.out.println(m.getUserPwd());
	 * 
	 * return "main"; }
	 */

	/*
	 * 5. @ModelAttribute 생략하고 객체를 바로 전달받는 방식 -
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public String
	 * loginMember(Member m, HttpSession session) {
	 * 
	 * 
	 * Member loginUser; try { loginUser = memberService.loginMember(m);
	 * System.out.println(loginUser); session.setAttribute("loginUser",loginUser);
	 * return "redirect:/"; } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return "common/errorPage";
	 * 
	 * 
	 * 
	 * }
	 */

	/* 응답페이지에 응답할 데이터가 있는 경우 */
	/*
	 * 1. Model 객체를 사용하는 방법 - 응답뷰로 전달하고자 하는 Map(키,밸류) 형식으로 담을 수 있다.
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public String
	 * loginMember(Member m, HttpSession session, Model model) { // Model 객체의 스코프는
	 * request이다
	 * 
	 * Member loginUser; try { loginUser = memberService.loginMember(m);
	 * System.out.println(loginUser); session.setAttribute("loginUser",loginUser);
	 * return "redirect:/"; } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); model.addAttribute("msg","로그인 실패"); } return
	 * "common/errorPage";
	 * 
	 * }
	 * 
	 */

	/*
	 * 2. ModelAndView 라는 객체를 사용하는 방법 - 값과 뷰를 모두 지정
	 * 
	 * @RequestMapping(value="login.me" , method=RequestMethod.POST) public
	 * ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) { //
	 * ModelAndView 타입으로 리턴한다.
	 * 
	 * Member loginUser; try { loginUser = memberService.loginMember(m);
	 * System.out.println(loginUser); session.setAttribute("loginUser",loginUser);
	 * mv.setViewName("redirect:/"); } catch (Exception e) { e.printStackTrace();
	 * 
	 * mv.addObject("msg","로그인실패"); mv.setViewName("common/errorPage"); }
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @RequestMapping("logout.me") public String logoutMember(HttpSession session)
	 * { session.invalidate();
	 * 
	 * return "redirect:/";
	 * 
	 * }
	 */

	/* 3. @SessionAttribute 어노테이션 사용하기 */

	/*
	 * @RequestMapping(value = "login.me", method = RequestMethod.POST) public
	 * String loginMember(Member m, Model model) { // Model 객체의 스코프는 request이다
	 * 
	 * Member loginUser; try { loginUser = memberService.loginMember(m);
	 * System.out.println(loginUser); model.addAttribute("loginUser", loginUser);
	 * return "redirect:/"; } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); model.addAttribute("msg", "로그인 실패"); } return
	 * "common/errorPage";
	 * 
	 * }
	 */

	@RequestMapping("logout.me")
	public String logoutMember(SessionStatus status) { // 세션의 상태를 관리하는 SessionStatus 객체
		status.setComplete(); // 현재 컨트롤러에 @SessionAttribute에 의해 저장된 오브젝트를 제거한다.

		return "redirect:/";

	}

	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}

	@RequestMapping("insert.me")
	public String insertMember(@ModelAttribute Member m, @RequestParam("post") String post,
			@RequestParam("address1") String address1, @RequestParam("address2") String address2, HttpSession session) {

		m.setAddress(post + "/" + address1 + "/" + address2);

		System.out.println("암호화 전  :" + m.getUserPwd());

		String encPwd = bCryptPasswordEncoder.encode(m.getUserPwd());
		System.out.println("암호화 후 : " + encPwd);
		m.setUserPwd(encPwd);

		memberService.insertMember(m);

		session.setAttribute("msg", "회원가입 성공");
		return "redirect:/";
	}

	@RequestMapping(value = "login.me", method = RequestMethod.POST)
	public String loginMember(Member m, Model model) {

		Member loginUser;
		loginUser = memberService.loginMember(bCryptPasswordEncoder, m);
		System.out.println(loginUser);
		model.addAttribute("loginUser", loginUser);
		return "redirect:/";

	}
	
	@RequestMapping("myPage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMemer(@ModelAttribute Member m, @RequestParam("post") String post,
														@RequestParam("address1") String address1,
														@RequestParam("address2") String address2,
														HttpSession session, Model model) throws Exception {
		
		m.setAddress(post + "/" + address1 +"/" +address2);
		
		Member userInfo = memberService.updateMember(m);
		model.addAttribute("loginUser",userInfo);
		
		
		return "member/myPage";
	} 	
	
	/*
	 * @RequestMapping("delete.me") public String deleteMember(@ModelAttribute
	 * Member m, SessionStatus status) {
	 * 
	 * memberService.deleteMember(m); status.setComplete(); // 현재
	 * 컨트롤러에 @SessionAttribute에 의해 저장된 오브젝트를 제거한다.
	 * 
	 * return "redirect:/";
	 * 
	 * }
	 */
	
	@RequestMapping("delete.me")
	public String deleteMember(String userId) {
		
		memberService.deleteMember(userId);

		return "redirect:logout.me";
		
	}
	
	

}
