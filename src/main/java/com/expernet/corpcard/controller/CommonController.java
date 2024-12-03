package com.expernet.corpcard.controller;


import com.expernet.corpcard.entity.User;
import com.expernet.corpcard.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
    /**
     * common Service
     */
    @Resource(name = "CommonService")
    private CommonService commonService;

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 사용자 정보 조회
     *
     * @param model     : Model
     * @param principal : 접속한 사용자 ID
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo(Model model, Principal principal) {
        User userInfo = null;
        try {
            userInfo = commonService.getUserInfo(principal.getName());
        } finally {
            if (userInfo != null) {
                model.addAttribute("result", userInfo);
                model.addAttribute("CODE", "SUCCESS");
                model.addAttribute("MSG", "사용자 정보 조회 성공");
                logger.info("사용자 정보 조회 성공.");
            } else {
                model.addAttribute("CODE", "ERR");
                model.addAttribute("MSG", "사용자 정보 조회 실패");
                logger.error("사용자 정보 조회 실패");
            }
        }
        return "jsonView";
    }
}
