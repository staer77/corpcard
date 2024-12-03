package com.expernet.corpcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "TB_ROOM")
@SequenceGenerator(name = "TB_ROOM_SEQ", sequenceName = "TB_ROOM_SEQ", initialValue = 1, allocationSize = 1) 
@NoArgsConstructor
public class Room{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ROOM_SEQ")
    @Column(name = "seq")
    private long seq;
    
    @Column(name = "deposit")
    private long deposit;
    
    @Column(name = "rent_price")
    private long rentPrice;
    
    @Column(name = "rent_type")
    private String rentType;

    @Column(name = "room_nm")
    private String roomNm;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "reg_id")
    private String regId;

    @Column(name = "udt_id")
    private String udtId;

    @Builder
    public Room(long seq, long deposit, long rentPrice, String rentType, String roomNm, String roomType, String regId, String udtId) {
        this.seq = seq;
        this.deposit = deposit;
        this.rentPrice = rentPrice;
        this.rentType = rentType;
        this.roomNm = roomNm;
        this.roomType = roomType;
        this.regId = regId;
        this.udtId = udtId;
    }
}
