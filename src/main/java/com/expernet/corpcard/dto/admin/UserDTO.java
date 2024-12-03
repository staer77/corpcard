package com.expernet.corpcard.dto.admin;

import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.entity.User;
import lombok.*;

import java.sql.Timestamp;

public class UserDTO {
	/**
     * 사용자 등록 Request
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String userNm;
        private String userId;
        private String userPw;
        private String phoneNum;
    }
	
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long seq;
        private String userId;
        private String userPw;
        private String userNm;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        @Builder
        public Response(User entity) {
            this.seq = entity.getSeq();
            this.userId = entity.getUserId();
            this.userPw = entity.getUserPw();
            this.userNm = entity.getUserNm();
        }
    }

  
}
