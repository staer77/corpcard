package com.expernet.corpcard.service;

import com.expernet.corpcard.entity.User;

public interface CommonService {
    /**
     * 사용자 정보 조회
     * @param userId : 로그인한 사용자 ID
     */
    User getUserInfo(String userId);
}
