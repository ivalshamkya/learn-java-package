package com.jobseeker.hrms.candidate.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UTCTimezoneSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			ZonedDateTime zonedUTC = date.toInstant().atZone(ZoneId.of("UTC"));
			jsonGenerator.writeString(zonedUTC.format(formatter));
		} else {
			jsonGenerator.writeNull();
		}
	}
}
