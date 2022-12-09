package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user1
 *
 */
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든필드 매개변수 생성자
@Setter //setter
@Getter //getter
@ToString //tostring
public class Member {
	private String userId;			//	USER_ID	VARCHAR2(30 BYTE)
	private String userPwd;		//	USER_PWD	VARCHAR2(100 BYTE)
	private String userName;		//	USER_NAME	VARCHAR2(15 BYTE)
	private String email;			//	EMAIL	VARCHAR2(100 BYTE)
	private String 	gender;		//	GENDER	VARCHAR2(1 BYTE)
	private String age; 			//	AGE	NUMBER
	private String 	phone;		//	PHONE	VARCHAR2(13 BYTE)
	private String 	address;		//	ADDRESS	VARCHAR2(100 BYTE)
	private Date enrollDate; 			//	ENROLL_DATE	DATE
	private Date modifyDate;			//	MODIFY_DATE	DATE
	private String status;			//	STATUS	VARCHAR2(1 BYTE)
	
	
}
