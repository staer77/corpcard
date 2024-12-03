package com.expernet.corpcard.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.expernet.corpcard.dto.admin.UserDTO;
import com.expernet.corpcard.entity.User;


public interface LoginService extends UserDetailsService {
	User insertUserInfo(UserDTO.Request params);
	
	User findUserId(String userId);
}
