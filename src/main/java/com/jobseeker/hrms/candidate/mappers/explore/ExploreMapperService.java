package com.jobseeker.hrms.candidate.mappers.explore;

import com.jobseeker.hrms.candidate.config.GlobalVariable;
import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.embedded.vacancy.JobFunctionDataEmbed;
import org.jobseeker.enums.general.PrefixURL;
import org.jobseeker.helper.CheckURLSourceFile;
import org.mapstruct.Context;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExploreMapperService {

    private final GlobalVariable globalVariable;

    @Named("addURLVideo")
    public String addPrefixURLVideo(String video) {
        return CheckURLSourceFile.addPrefixURL(video, PrefixURL.VIDEO);
    }

    @Named("addURLVideoThumbnail")
    public String addPrefixURLVideoThumbnail(String thumbnail) {
        return CheckURLSourceFile.addPrefixURL(thumbnail, PrefixURL.THUMBNAIL);
    }

    @Named("addURLPhoto")
    public String addPrefixURLPhoto(String photo) {
        return CheckURLSourceFile.addPrefixURL(photo, PrefixURL.PHOTO);
    }

    @Named("setFromMyPool")
    public Boolean setFromMyPool(List<CompanyDataEmbed> companyDataEmbeds) {
        if (companyDataEmbeds == null) return false;
        return companyDataEmbeds.stream()
                .anyMatch(company -> ServletRequestAWS.getCompanyId().equals(Optional.ofNullable(company.get_id()).orElse("")));
    }

    @Named("getDistance")
    public int calculateDistance(GeoJsonPoint coordinate) {
        int EARTH_RADIUS = 6371;
        // current point
        double latCurrent = Math.toRadians(globalVariable.getLat());
        double lonCurrent = Math.toRadians(globalVariable.getLon());
        // target point
        double latTarget = 0, lonTarget = 0;
        if (coordinate != null) {
            latTarget = Math.toRadians(coordinate.getY());
            lonTarget = Math.toRadians(coordinate.getX());
        }
        // calculate distance
        double x = (lonTarget - lonCurrent) * Math.cos((latTarget + latCurrent) / 2);
        double y = (latTarget - latCurrent);
        double distance = Math.sqrt(x * x + y * y) * EARTH_RADIUS;
        DecimalFormat formatter = new DecimalFormat("#.000");
        double distanceFormat = Double.parseDouble(formatter.format(distance));
        // 1000 mean meter
        double result = distanceFormat * 1000;
        return (int) result;
    }

    @Named("setPosition")
    public String setPosition(Candidate candidate, @Context List<String> jobFunctionIds, @Context String keyword) {
        String position = "";
        try {
            if (candidate == null) return null;

            if (candidate.getJobFunction() != null) {
                Object jobFunctionName;
                if (!candidate.getJobFunction().isEmpty()) {
                    if (!(keyword == null)) {
                        jobFunctionName = Optional.of(candidate)
                                .map(Candidate::getJobFunction)
                                .flatMap(jobFunctions -> jobFunctions.stream()
                                        .filter(jobFunc -> checkJobFunc(jobFunc, keyword))
                                        .map(GeneralMultiLangDataEmbed::getName)
                                        .filter(Objects::nonNull)
                                        .findFirst())
                                .orElse(null);
                    } else {
                        jobFunctionName = Optional.of(candidate)
                                .map(Candidate::getJobFunction)
                                .flatMap(jobFunctions -> jobFunctions.stream()
                                        .filter(jobFunc -> jobFunc != null && jobFunctionIds.contains(jobFunc.get_id()))
                                        .map(GeneralMultiLangDataEmbed::getName)
                                        .filter(Objects::nonNull)
                                        .findFirst())
                                .orElse(null);
                    }
                    if (jobFunctionName == null) {
                        jobFunctionName = Optional.of(candidate)
                                .map(Candidate::getJobFunction)
                                .map(List::getLast)
                                .map(GeneralMultiLangDataEmbed::getName)
                                .orElse("");
                    }
                    String jobFunctionEnName = getObjectMultiLang(jobFunctionName, "en");
                    String jobFunctionIdName = getObjectMultiLang(jobFunctionName, "id");

                    position = jobFunctionEnName + jobFunctionIdName;
                }
            }

            if (position.isEmpty()) {
                position = Optional.of(candidate)
                        .map(Candidate::getPosition).orElse("-");
            }

            return position;
        } catch (Exception ex) {
            return "ENULL";
        }
    }

    private boolean checkJobFunc(JobFunctionDataEmbed jobFunc, String keyword) {
        if (jobFunc == null) return false;
        String jobFuncStr = jobFunc.getName().toString().toLowerCase();
        String keywordStr = keyword.toLowerCase();
        return jobFuncStr.contains(keywordStr);
    }

    private String getObjectMultiLang(Object objectValue, String lang) {
        String dataName;
        if (objectValue instanceof ObjectMultiLanguage obj) {
            Map<String, String> map = obj.toMap();
            dataName = map.get(lang);
        } else if (objectValue instanceof Map<?, ?> obj) {
            dataName = (String) obj.get(lang);
        } else {
            dataName = objectValue.toString();
        }

        if (lang.equals("id") && !dataName.isEmpty()) return " (" + dataName + ")";
        return dataName;
    }
}
