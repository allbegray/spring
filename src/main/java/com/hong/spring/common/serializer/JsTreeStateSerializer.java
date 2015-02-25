package com.hong.spring.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hong.spring.common.jstree.JsTreeState;

public class JsTreeStateSerializer extends JsonSerializer<JsTreeState> {

	@Override
	public void serialize(JsTreeState value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(value.name().toLowerCase());
	}

}
