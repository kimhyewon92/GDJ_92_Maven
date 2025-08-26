package com.winter.app.member;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.winter.app.home.HomeController;
import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;
import com.winter.app.products.ProductVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/member/*")
@Slf4j
public class MemberController {

    private final HomeController homeController;
	
	@Autowired
	private MemberService memberService;

    MemberController(HomeController homeController) {
        this.homeController = homeController;
    }
	
	@GetMapping("join")
	public void join(MemberVO memberVO) throws Exception {}
	
	@PostMapping("join")
	public String join(@Validated({AddGroup.class, UpdateGroup.class}) MemberVO memberVO, BindingResult bindingResult, MultipartFile profile) throws Exception {
		
		boolean check = memberService.hasMemberError(memberVO, bindingResult);
		
		if(check) {
			return "member/join";
		}
		
		int result = memberService.join(memberVO, profile);
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login(Principal principal) throws Exception {
		if(principal != null) {
			return "redirect:/";
		}
		
		return "member/login";
	}

// config 사용, 이제 안씀 -> security filter 대신함
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session) throws Exception{
//		memberVO = memberService.login(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		return "redirect:/";
//	}
	
	//spring security 가 대신
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		session.invalidate();
//		return "redirect:/";
//	}
	
	@GetMapping("update")
	public String update(Principal principal, MemberVO memberVO, Model model) throws Exception {
//		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		return "member/memberUpdate";
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) MemberVO memberVO, BindingResult bindingResult, MultipartFile profile, Principal principal) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "member/memberUpdate";
		}
		
//		MemberVO sessionMember =(MemberVO)session.getAttribute("member");
//		memberVO.setUsername(sessionMember.getUsername());
		int result = memberService.update(memberVO);
		
		// 업데이트된 정보로 확인이 안되어서..
		if(result>0) {
			memberVO.setPassword(sessionMember.getPassword());
			memberVO = memberService.login(memberVO);
			session.setAttribute("member", memberVO);
		}
		return "redirect:./detail";
	}
	
	@GetMapping("detail")
	public void detail() throws Exception{}
	
	@PostMapping("cartAdd")
	@ResponseBody
	public int cartAdd(Long productNum, HttpSession session, Model model)throws Exception{
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("productNum", productNum);
		model.addAttribute(map);
		
		return memberService.cartAdd(map);
		
	}
	
	@GetMapping("cartList")
	public void cartList(HttpSession session, Model model) throws Exception{
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		List<ProductVO> list = memberService.cartList(memberVO);
		model.addAttribute("list", list);
	}
	
	@PostMapping("cartDelete")
	public String cartDelete(Long [] productNum, HttpSession session)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		int result = memberService.cartDelete(productNum, memberVO);
		
		return "redirect:./cartList";
	}
}
