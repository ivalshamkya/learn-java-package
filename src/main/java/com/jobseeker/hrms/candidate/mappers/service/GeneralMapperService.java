package com.jobseeker.hrms.candidate.mappers.service;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.general.embed.PhoneItem;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.embedded.master.PhoneDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.helper.ObjectMapperHelper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GeneralMapperService {

    @Named("setGeneralDataFromMultiLang")
    public GeneralDataEmbed setGeneralDataFromMultiLang(GeneralMultiLangDataEmbed data) {
        if (data == null) return null;
        String language = ServletRequestAWS.getLanguage();
        Object nameObj = Optional.ofNullable(data.getName()).orElse("-");

        String dataName;
        if (nameObj instanceof ObjectMultiLanguage obj) {
            Map<String, String> map = obj.toMap();
            dataName = map.get(language);
        } else if (nameObj instanceof Map<?, ?> obj) {
            dataName = (String) obj.get(language);
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
        String language = ServletRequestAWS.getLanguage();

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

    @Named("setGeneralDataFromObject")
    public GeneralDataEmbed setGeneralData(Object data) {
        if (data == null) return null;

        GeneralDataEmbed dataEmbed = new GeneralDataEmbed();

        if (data instanceof GeneralDataEmbed obj) {
            dataEmbed = obj;
        }

        if (data instanceof LinkedHashMap<?, ?> obj) {
            dataEmbed = ObjectMapperHelper.Convert(obj, GeneralDataEmbed.class);
        }

        if (data instanceof String objStr) {
            dataEmbed.set_id(null);
            dataEmbed.setName(objStr);
        }

        return dataEmbed;
    }

    @Named("setFileDataEmbedResponse")
    public FileDataEmbed setFileDataEmbedResponse(Object data) {
        try {
            if (data instanceof LinkedHashMap<?, ?> obj) {
                return ObjectMapperHelper.Convert(obj, FileDataEmbed.class);
            } else if (data instanceof FileDataEmbed obj) {
                return obj;
            } else {
                FileDataEmbed cvFile = new FileDataEmbed();
                cvFile.setLink(data.toString());
                return cvFile;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Named("stringToString")
    public String stringToString(String data) {
        if (data == null) return null;
        return data;
    }

    @Named("instantToStrDate")
    public String instantToStrDate(Date dob) {
        LocalDate localDate = dob.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    @Named("setPhoneItem")
    public PhoneItem setPhoneItem(PhoneDataEmbed phoneData) {
        if (phoneData == null) return null;
        return PhoneItem.builder()
                .countryCode(phoneData.getCountryCode())
                .phoneNumber(phoneData.getPhone())
                .build();
    }
}
