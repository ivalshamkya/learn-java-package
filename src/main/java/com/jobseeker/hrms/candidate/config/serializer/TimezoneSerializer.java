package com.jobseeker.hrms.candidate.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jobseeker.hrms.candidate.config.ServletRequestAWS;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class 	TimezoneSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			ZoneId clientZone = ZoneId.of(ServletRequestAWS.getTimezone());

			// Convert Date to ZonedDateTime
			ZonedDateTime zonedUTC = date.toInstant().atZone(ZoneId.of("UTC"));
			ZonedDateTime zonedTimezone = zonedUTC.withZoneSameInstant(clientZone);

			jsonGenerator.writeString(zonedTimezone.format(formatter));
		} else {
			jsonGenerator.writeNull();
		}
	}
}