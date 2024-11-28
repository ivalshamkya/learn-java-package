package com.jobseeker.hrms.candidate.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jobseeker.hrms.candidate.config.ServletRequestAWS;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTimezoneSerializer extends JsonSerializer<LocalDateTime> {
	@Override
	public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (localDateTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			ZoneId clientZone = ZoneId.of(ServletRequestAWS.getTimezone());

			// Convert Date to ZonedDateTime
			ZonedDateTime zonedUTC = localDateTime.atZone(ZoneId.of("UTC"));
			ZonedDateTime zonedTimezone = zonedUTC.withZoneSameInstant(clientZone);

			jsonGenerator.writeString(zonedTimezone.format(formatter));
		} else {
			jsonGenerator.writeNull();
		}
	}
}