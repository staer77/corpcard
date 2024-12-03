package com.expernet.corpcard.service.Impl;

import com.expernet.corpcard.entity.*;
import com.expernet.corpcard.repository.*;
import com.expernet.corpcard.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service("CommonService")
public class CommonServiceImpl implements CommonService {
    /**
     * 사용자 Repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 사용자 정보 조회
     *
     * @param userId : 로그인한 사용자 ID
     */
    @Override
    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId);
    }

    
}
