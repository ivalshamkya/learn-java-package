package com.jobseeker.hrms.candidate.mappers.service;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.helper.ObjectMapperHelper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;

@Component
public class HelperMapperService {

    public static String setMultiLang(Object param) {
        if (param instanceof String) {
            return (String) param;
        } else if (param instanceof LinkedHashMap || param instanceof ObjectMultiLanguage) {
            ObjectMultiLanguage obj = ObjectMapperHelper.Convert(param, ObjectMultiLanguage.class);
            if (ServletRequestAWS.getLanguage().equalsIgnoreCase("en")) return obj.getEn();
            return obj.getId();
        } else {
            return null;
        }
    }

    public static Instant convertToInstant(String dateStr) {
        if (dateStr != null) {
            if (dateStr.length() > 10) dateStr = dateStr.substring(0, 10);
            LocalDateTime localDate = LocalDateTime.parse(dateStr + "T00:00:00");
            return localDate.atZone(ZoneId.of("UTC")).toInstant();
        } else {
            return null;
        }
    }

    public static String getFileExtension(String url) {
        int lastIndexOfSlash = url.lastIndexOf('/');
        if (lastIndexOfSlash >= 0 && lastIndexOfSlash < url.length() - 1) {
            String fileName = url.substring(lastIndexOfSlash + 1);
            int lastIndexOfDot = fileName.lastIndexOf('.');
            if (lastIndexOfDot >= 0 && lastIndexOfDot < fileName.length() - 1) {
                return fileName.substring(lastIndexOfDot + 1);
            }
        }
        return "";
    }
}
