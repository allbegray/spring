package com.hong.spring.common.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public abstract class DateSerializer extends JsonSerializer<Date> {

	public abstract String getPattern();

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(this.getPattern());
		String dateString = dateFormat.format(value);
		jgen.writeString(dateString);
	}

}