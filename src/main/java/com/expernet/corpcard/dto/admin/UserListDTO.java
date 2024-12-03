package com.expernet.corpcard.dto.admin;

import com.expernet.corpcard.entity.User;
import lombok.*;

import java.sql.Timestamp;

public class UserListDTO {
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
