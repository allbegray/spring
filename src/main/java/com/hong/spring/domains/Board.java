package com.hong.spring.domains;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.DateyyyyMMddHHmmssSerializer;

public class Board {

	private Long id;
	private String title;
	private String desc;
	private Date createDt;

	public Board() {
	}

	public Board(Long id, String title, String desc, Date createDt) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.createDt = createDt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

}