package com.jobseeker.hrms.candidate.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.basic.response.component.ExperienceDataResponse;
import org.jobseeker.embedded.candidate.VideoResumeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.embedded.master.ExperienceDataEmbed;
import org.jobseeker.embedded.master.PhoneDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.helper.EducationCodeHelper;
import org.jobseeker.helper.GeneralHelper;
import org.jobseeker.helper.ObjectMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    @Mapping(target = "name", qualifiedByName = "setNameFromMultiLang")
    GeneralDataEmbed toGeneralDataEmbed(GeneralMultiLangDataEmbed generalMultiLangDataEmbed);

    @Named("setNameFromMultiLang")
    default String setNameFromMultiLang(Object data) {
        if (data == null) return null;
        String language = ServletRequestAWS.getLanguage();

        String dataName;
        if (data instanceof ObjectMultiLanguage obj) {
            Map<String, String> map = obj.toMap();
            dataName = map.get(language);
        } else if (data instanceof Map<?, ?> obj) {
            dataName = (String) obj.get(language);
        } else {
            dataName = data.toString();
        }

        return dataName;
    }

    @Named("setAge")
    default String setAge(Date birthdate) {
        if (birthdate == null) {
            return null;
        }

        LocalDate birthDateLocal = LocalDateTime.ofInstant(birthdate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (birthDateLocal.isAfter(currentDate)) {
            return "Invalid birthdate";
        }

        int age = Period.between(birthDateLocal, currentDate).getYears();
        return String.valueOf(age);
    }

    @Named("setMultiLang")
    default String setMultiLang(Object param) {
        if (param instanceof String) {
            return (String) param;
        } else if (param instanceof LinkedHashMap res) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectMultiLanguage obj = mapper.convertValue(res, ObjectMultiLanguage.class);
            return obj.getEn();
        } else {
            return null;
        }
    }

    @Named("setPhoneNumber")
    default String setPhoneNumber(PhoneDataEmbed param) {
        if (param == null) return null;
        if (param.getPhone() == null) return null;
        return param.getPhone();
    }

    @Named("setFileDataEmbedToString")
    default String setFileDataEmbedToString(Object param) {
        switch (param) {
            case String s -> {
                return s;
            }
            case VideoResumeDataEmbed res -> {
                VideoResumeDataEmbed obj = ObjectMapperHelper.Convert(res, VideoResumeDataEmbed.class);
                return obj.getLink();
            }
            case FileDataEmbed res -> {
                FileDataEmbed obj = ObjectMapperHelper.Convert(res, FileDataEmbed.class);
                return obj.getLink();
            }
            case null, default -> {
                return null;
            }
        }
    }

    @Named("dateToLocalDateTime")
    default LocalDateTime dateLocalDateTime(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return GeneralHelper.stringToLocalDateTime(dateStr);
    }

    @Named("setStringToDate")
    default Date setStringToDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return GeneralHelper.convertToDate(dateStr);
    }

    @Named("setExperienceResponse")
    default ExperienceDataResponse setExperienceResponse(List<ExperienceDataEmbed> embeddedList) {
        if (embeddedList == null || embeddedList.isEmpty()) return null;

        embeddedList.sort(Comparator
                .comparing(ExperienceDataEmbed::getEndDate, Comparator.nullsLast(Comparator.naturalOrder()))
                .reversed());

        ExperienceDataEmbed data = embeddedList.getFirst();

        ExperienceDataResponse res = new ExperienceDataResponse();
        res.setCompanyName(data.getCompanyName());
        res.setPosition(data.getPosition());
        res.setStartDate(data.getStartDate());
        res.setEndDate(data.getEndDate());
        res.set_id(data.get_id());
        res.setJobType(data.getJobType());
        res.setJobDescription(data.getJobDescription());
        res.setStillWorkHere(data.getIsStillWorkHere());
        res.setWorkingPeriodInMonths(data.getWorkingPeriodInMonths());

        return res;
    }

    @Named("setExperiencesResponse")
    default List<ExperienceDataResponse> setExperiencesResponse(List<ExperienceDataEmbed> experienceDataEmbeds) {
        if (experienceDataEmbeds == null || experienceDataEmbeds.isEmpty()) return new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        experienceDataEmbeds.sort(Comparator
                .comparing(ExperienceDataEmbed::getEndDate, Comparator.nullsLast(Comparator.naturalOrder()))
                .reversed());

        List<ExperienceDataResponse> experienceDataRespons = new ArrayList<>();
        for (ExperienceDataEmbed data : experienceDataEmbeds) {
            ExperienceDataResponse res = new ExperienceDataResponse();

            // Parse the JSON from companyName to extract the "name" field
            String companyNameJson = data.getCompanyName();
            String companyName = null;
            try {
                JsonNode rootNode = objectMapper.readTree(companyNameJson);
                companyName = rootNode.path("name").asText();
            } catch (Exception e) {
                companyName = companyNameJson;
            }

            res.setCompanyName(companyName);
            res.setPosition(data.getPosition());
            res.setStartDate(data.getStartDate());

            res.setEndDate(data.getEndDate());
            if (data.getIsStillWorkHere()) res.setEndDate(GeneralHelper.convertToDate(String.valueOf(LocalDate.now())));

            res.set_id(data.get_id());
            res.setJobType(data.getJobType());
            res.setJobDescription(data.getJobDescription());
            res.setStillWorkHere(data.getIsStillWorkHere());
            res.setIsCurrentlyWorking(data.getIsStillWorkHere());
            res.setWorkingPeriodInMonths(data.getWorkingPeriodInMonths());
            experienceDataRespons.add(res);
        }
        return experienceDataRespons;
    }

    @Named("AbbrevationEducation")
    static String AbbrevationEducation(String id) {
        if (id == null) return null;
        return EducationCodeHelper.EDUCATION_CODE_ID.get(id);
    }

}
