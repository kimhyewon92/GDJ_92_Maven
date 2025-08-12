package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;

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
}
