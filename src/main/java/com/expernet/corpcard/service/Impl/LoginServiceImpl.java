package com.expernet.corpcard.service.Impl;


import com.expernet.corpcard.dto.admin.UserDTO;
import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.entity.Room;
import com.expernet.corpcard.entity.User;
import com.expernet.corpcard.config.Constant;
import com.expernet.corpcard.config.JwtService;
import com.expernet.corpcard.repository.UserRepository;
import com.expernet.corpcard.service.LoginService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    /**
     * User Repository
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private HttpServletResponse response;
    
    /**
     * load Security UserDetails
     * @param userId : user ID
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	
        User userinfo = userRepository.findByUserId(userId);
        List<String> roles = new ArrayList<>();

        if (userinfo == null) {
            throw new UsernameNotFoundException("해당 사용자가 없습니다.");
        }
        
        String token = jwtService.createToken(userId, 60*1000);
        response.setHeader(Constant.AUTH_HEADER, token);
        
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(userId)
                .password(userinfo.getUserPw())
                .roles(roles.toArray(new String[0])).build();
    }
    
    @Override
	@Transactional
    public User insertUserInfo(UserDTO.Request params) {
    	User result = null;
        
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(params.getUserPw());
    	
        User user = User.builder()
        		.userNm(params.getUserNm())
        		.userId(params.getUserId())
        		.userPw(encodedPassword)
        		.phoneNum(params.getPhoneNum())
        		.build();
        
        result = userRepository.save(user);
        return result;
    }
    
    public User findUserId(String userId) {
    	return userRepository.findByUserId(userId);
    }
}
