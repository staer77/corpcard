package com.expernet.corpcard.dto.main;

import com.expernet.corpcard.entity.Room;

import lombok.*;

import java.sql.Timestamp;

public class RoomDTO {
	
	/**
     * 방 등록 Request
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
    	private String rentType;
        private String roomType;
        private long deposit;
        private long rentPrice;
        private String roomNm;
        private String regId;
    }
    
    /**
     * 방 수정(조회) Request
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class selectRequest {
        private long seq;
    }
    
    /**
     * 방 수정 Request
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class udRequest {
    	private long seq;
    	private String rentType;
        private String roomType;
        private long deposit;
        private long rentPrice;
        private String roomNm;
        private String udtId;
    }
}
