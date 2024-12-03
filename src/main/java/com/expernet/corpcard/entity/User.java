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
@Table(name = "TB_USER")
@SequenceGenerator(name = "TB_USER_SEQ", sequenceName = "TB_USER_SEQ", initialValue = 1, allocationSize = 1) 
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USER_SEQ")
    @Column(name = "SEQ")
    private long seq;
    
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NM")
    private String userNm;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    @Builder
    public User(long seq, String userId, String userNm, String userPw, String phoneNum) {
        this.seq = seq;
        this.userId = userId;
        this.userNm = userNm;
        this.userPw = userPw;
        this.phoneNum = phoneNum;
    }
}
