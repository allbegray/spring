package com.hong.spring.domains;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.domain.Creatable;
import com.hong.spring.common.domain.Modifiable;
import com.hong.spring.common.serializer.DateyyyyMMddHHmmssSerializer;

public class Board implements Creatable, Modifiable {

	private Long id;
	private String title;
	private String desc;
	private String createId;
	private Date createDt;
	private String modifierId;
	private Date modifierDt;

	public Board() {
	}

	@Override
	public String getModifierId() {
		return modifierId;
	}

	@Override
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	@Override
	public Date getModifierDt() {
		return modifierDt;
	}

	@Override
	public void setModifierDt(Date modifierDt) {
		this.modifierDt = modifierDt;
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

	@Override
	public String getCreateId() {
		return createId;
	}

	@Override
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Override
	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getCreateDt() {
		return createDt;
	}

	@Override
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}