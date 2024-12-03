package com.expernet.corpcard.dto.main;

import com.expernet.corpcard.entity.Room;
import lombok.*;

import java.sql.Timestamp;

public class RoomListDTO {
	
	/**
     * 방 목록 조회 Request
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private long rentPrice1;
        private long rentPrice2;
        private String rentType;
        private String roomType;
    }
	
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long seq;
        private long deposit;
        private long rentPrice;
        private String rentType;
        private String roomNm;
        private String roomType;

        @Builder
        public Response(Room entity) {
            this.seq = entity.getSeq();
            this.deposit = entity.getDeposit();
            this.rentPrice = entity.getRentPrice();
            this.rentType = entity.getRentType();
            this.roomNm = entity.getRoomNm();
            this.roomType = entity.getRoomType();
        }
    }
}
