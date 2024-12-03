package com.expernet.corpcard.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expernet.corpcard.dto.admin.UserDTO;
import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.entity.Room;
import com.expernet.corpcard.entity.User;
import com.expernet.corpcard.service.LoginService;
import com.expernet.corpcard.service.RoomService;


@Controller
public class LoginController {
	/**
	 * Login Service
	 */
	@Resource(name = "loginService")
	private LoginService loginService;

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
    /**
     * 로그인 페이지 이동
     */
    @RequestMapping("/viewLogin")
    public String viewLogin() {
        return "login";
    }
    
    /**
	 * 로그인
	 *
	 * @param params : 등록 내용
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userId, String userPw, ModelMap model) {
		UserDetails result = null;
		try {
			result = loginService.loadUserByUsername(userId);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","로그인 성공");
				logger.info("로그인 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "로그인 실패");
				logger.info("로그인 실패");
			}
		}
		return "jsonView";
	}
    
    /**
	 * 사용자 등록
	 *
	 * @param params : 등록 내용
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/login/insert", method = RequestMethod.POST)
	public String insertUser(@Valid UserDTO.Request param, ModelMap model) {
		User result = null;
		try {
			result = loginService.insertUserInfo(param);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","회원 등록 성공");
				logger.info("회원 등록 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "회원 등록 실패");
				logger.info("회원 등록 실패");
			}
		}
		return "jsonView";
	}
	
	/**
	 * ID 조회
	 *
	 * @param params : 등록 내용
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/login/info", method = RequestMethod.GET)
	public String findUserId(String userId, ModelMap model) {
		User result = null;
		try {
			result = loginService.findUserId(userId);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","회원 조회 성공");
				logger.info("회원 조회 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "회원 조회 실패");
				logger.info("회원 조회 실패");
			}
		}
		return "jsonView";
	}
}
