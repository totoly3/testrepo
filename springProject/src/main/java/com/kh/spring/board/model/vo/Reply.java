package com.kh.spring.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor 
@Data
public class Reply {
	private int replyNo;			//	REPLY_NO	NUMBER
	private String replyContent;			//	REPLY_CONTENT	VARCHAR2(400 BYTE)
	private int refBno;			//	REF_BNO	NUMBER
	private String replyWriter;			//	REPLY_WRITER	VARCHAR2(30 BYTE)
	private Date createDate;			//	CREATE_DATE	DATE
	private String status;			//	STATUS	VARCHAR2(1 BYTE)
	private String reparent; //부ㅠ모댓글번호			//	REPARENT
	private String redepth;	//댓글깊이						//	REDEPTH
	private Integer reorder;	//댓글순서						//	REORDER
	
	
	
}
