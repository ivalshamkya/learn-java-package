package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.general.ObjectMultiLanguageDataResponse;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.helper.ObjectMapperHelper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GeneralMapperService {

	@Named("setMultiLangToDataResponse")
	public GeneralDataEmbed setMultiLangToDataResponse(GeneralMultiLangDataEmbed data) {
		if(data == null) return null;
		String language = ServletRequestAWS.getLanguage();
		Object nameObj = Optional.ofNullable(data.getName()).orElse("-");

		String dataName;
		if (nameObj instanceof ObjectMultiLanguage obj) {
			Map<String, String> map = obj.toMap();
			dataName = map.get(language);
		} else {
			dataName = nameObj.toString();
		}

		GeneralDataEmbed dataEmbed = new GeneralDataEmbed();
		dataEmbed.set_id(data.get_id());
		dataEmbed.setName(dataName);

		return dataEmbed;
	}

	// best practice
	@Named("setMultiLangToObject")
	public Object setMultiLangToObject(Object data) {
		if (data == null) return null;
		String language = ServletRequestAWS.getGeneralLanguage();

		if (!language.equals("all")) {
			ObjectMultiLanguage obj = ObjectMapperHelper.Convert(data, ObjectMultiLanguage.class);
			if (language.equals("en")){
				return obj.getEn();
			}
			return obj.getId();
		} else {
			return data;
		}
	}

	@Named("setToLocalDateTime")
	public LocalDateTime setToLocalDateTime(String dateStr) {
		if (dateStr == null) return null;
		return LocalDateTime.parse(dateStr);
	}

	@Named("setObjectToGeneralData")
	public GeneralDataEmbed setObjectToGeneralData(Object data) {
		if (data == null) return null;
		return ObjectMapperHelper.Convert(data, GeneralDataEmbed.class);
	}

}
