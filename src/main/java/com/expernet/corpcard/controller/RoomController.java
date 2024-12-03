package com.expernet.corpcard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.dto.main.RoomListDTO;
import com.expernet.corpcard.entity.Room;
import com.expernet.corpcard.service.RoomService;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/room")
public class RoomController {
	
	/**
	 * Room Service
	 */
	@Resource(name = "roomService")
	private RoomService roomService;

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	/**
	 * 방 목록 조회
	 *
	 * @param params : 검색 조건
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getRoomList(@Valid RoomListDTO.Request param, ModelMap model) {
		List<Room> result = null;
		try {
			result = roomService.roomList(param);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","방 목록 조회 성공");
				logger.info("방 목록 조회 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "방 목록 조회 실패");
				logger.info("방 목록 조회 실패");
			}
		}
		return "jsonView";
	}
	
	/**
	 * 방 상세 조회
	 *
	 * @param params : 검색 조건
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String selectRoom(@Valid RoomDTO.selectRequest param, ModelMap model) {
		Room result = null;
		try {
			result = roomService.selectRoom(param);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","방 상세 조회 성공");
				logger.info("방 상세 조회 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "방 상세 조회 실패");
				logger.info("방 상세 조회 실패");
			}
		}
		return "jsonView";
	}
	
	/**
	 * 방 등록
	 *
	 * @param params : 등록 내용
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertRoom(@Valid RoomDTO.Request param, ModelMap model) {
		Room result = null;
		try {
			result = roomService.insertRoomInfo(param);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","방 목록 등록 성공");
				logger.info("방 목록 등록 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "방 목록 등록 실패");
				logger.info("방 목록 등록 실패");
			}
		}
		return "jsonView";
	}
	
	/**
	 * 방 수정
	 *
	 * @param params : 등록 내용
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateRoom(@Valid RoomDTO.udRequest param, ModelMap model) {
		Room result = null;
		try {
			result = roomService.updateRoomInfo(param);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","방 목록 수정 성공");
				logger.info("방 목록 수정 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "방 목록 수정 실패");
				logger.info("방 목록 수정 실패");
			}
		}
		return "jsonView";
	}
	
	/**
	 * 방 삭제
	 *
	 * @param seq : seq
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String deleteRoom(long seq, ModelMap model) {
		try {
			roomService.deleteRoomInfo(seq);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "jsonView";
	}
	
	/**
	 * 내 방 목록 조회
	 *
	 * @param params : 검색 조건
	 * @param model  : modelMap
	 */
	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public String getMyRoomList(String regId, ModelMap model) {
		List<Room> result = null;
		try {
			result = roomService.myRoomList(regId);
		} finally {
			if (result != null) {
				model.addAttribute("result", result);
				model.addAttribute("CODE", "SUCCESS");
				model.addAttribute("MSG","내 방 목록 조회 성공");
				logger.info("내 방 목록 조회 성공.");
			} else {
				model.addAttribute("CODE", "EMPTY");
				model.addAttribute("MSG", "내 방 목록 조회 실패");
				logger.info("내 방 목록 조회 실패");
			}
		}
		return "jsonView";
	}
}
