package com.hong.spring.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hong.spring.common.jstree.v1.JsTreeV1State;

public class JsTreeV1StateSerializer extends JsonSerializer<JsTreeV1State> {

	@Override
	public void serialize(JsTreeV1State value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(value.name().toLowerCase());
	}

}
