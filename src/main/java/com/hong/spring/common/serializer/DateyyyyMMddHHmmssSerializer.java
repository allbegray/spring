package com.hong.spring.common.serializer;

public class DateyyyyMMddHHmmssSerializer extends DateSerializer {

	public String getPattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}

}