package com.winter.app.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.products.ProductVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.member}")
	private String board;
	
	// 검증 메서드
	public boolean hasMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		boolean check = false;
		// check: true  => 검증 실패
		// check: false => 검증 통과
		
		// 1. annotation 검증
		check = bindingResult.hasErrors();
		
		// 2. 사용자 정의로 패스워드가 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())){
			check = true;
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual"); // 변수, 코드(properties key 문자열로)
//			bindingResult.rejectValue("passwordCheck", "member.password.notEqual"); //properties key 없으면 default로 메세지 지정하여 사용
		}
		
		// 3. ID 중복 검사
		MemberVO memberCheck = memberDAO.login(memberVO);
		
		if (memberCheck != null) {
			check = true;
			bindingResult.rejectValue("username", "member.id.equal");
		}
		return check;
	}
	
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberDAO.join(memberVO);
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUsername(memberVO.getUsername());
		profileVO.setOriName("default.jpg");
		profileVO.setSaveName("default.jpg");
		
		if(profile != null && !profile.isEmpty()) {
			profileVO.setSaveName(fileManager.fileSave(upload+board, profile));
			profileVO.setOriName(profile.getOriginalFilename());
		}
		
		result = memberDAO.profileInsert(profileVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 3);
		
		result = memberDAO.addRole(map);
		
		return result;
	}
	
	public MemberVO login(MemberVO memberVO) throws Exception{
		MemberVO checkVO = memberDAO.login(memberVO);

		
		if(checkVO != null && memberVO.getPassword().equals(checkVO.getPassword())){
			
			return checkVO;
		}
		
		return null;
	}
	
	public int cartAdd(Map<String, Object> map)throws Exception{
		return memberDAO.cartAdd(map);
	}
	
	public List<ProductVO> cartList(MemberVO memberVO) throws Exception{
		// 나중에 페이징 처리 해야 함
		return memberDAO.cartList(memberVO);
	}
	
	public int cartDelete(Long [] productNum, MemberVO memberVO) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("list", Arrays.asList(productNum));
	
		return memberDAO.cartDelete(map);
	}
	
	public int update(MemberVO memberVO) throws Exception {
		return memberDAO.update(memberVO);
	}
	
}
