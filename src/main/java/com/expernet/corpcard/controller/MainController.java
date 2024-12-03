package com.expernet.corpcard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expernet.corpcard.dto.main.RoomListDTO;
import com.expernet.corpcard.entity.Room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping(value = "/main")
public class MainController {
	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * 메인 페이지
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mainView(Model model) {
		return "main";
	}
	
	/**
	 * 등록 페이지
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertView(Model model) {
		return "insert";
	}
	
	/**
	 * 수정 페이지
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateView(Model model, HttpServletRequest req) {
		String seq = req.getParameter("seq");
        if (seq != null) {
            model.addAttribute("seq", seq);
        }
		return "update";
	}
}
