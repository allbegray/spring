package com.hong.spring.common.domain;

import java.util.Date;

public interface Modifiable {

	String getModifierId();

	void setModifierId(String modifierId);

	Date getModifierDt();

	void setModifierDt(Date modifierDt);

}
