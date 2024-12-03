package com.expernet.corpcard.config;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResultDTO {
	private boolean result;
	private String msg;
	private Object object;
	
	public ResultDTO(boolean result, String msg, Object object) {
		this.result = result;
		this.msg = msg;
		this.object = object;
	}
}
