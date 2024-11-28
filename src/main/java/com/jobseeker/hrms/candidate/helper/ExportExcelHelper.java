package com.jobseeker.hrms.candidate.helper;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.EducationDataEmbed;
import org.jobseeker.embedded.master.ExperienceDataEmbed;
import org.jobseeker.embedded.master.GenderDataEmbed;
import org.jobseeker.embedded.master.PhotoProfileDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.helper.MultiLangHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class ExportExcelHelper {

    //<editor-fold desc="Prettify Header">
    public static String PrettifyHeader(String header) {
        String[] words = header.split("_");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
    //</editor-fold>

    //<editor-fold desc="General String Value">
    public static String SetString(Object obj) {
        try {
            return obj != null ? obj.toString() : "";
        } catch (Exception e) {
            System.err.println("Error in getStringValue: " + e.getMessage());
            return "";
        }
    }
    //</editor-fold>

    //<editor-fold desc="Date">
    public static String SetDate(LocalDateTime localDateTime) {
        try {
            return localDateTime != null ? localDateTime.toLocalDate().toString() : "";
        } catch (Exception e) {
            System.err.println("Error in getStringValue: " + e.getMessage());
            return "";
        }
    }
    //</editor-fold>

    //<editor-fold desc="Gender">
    public static String SetGender(GenderDataEmbed gender) {
        try {
            if (gender != null && gender.getName() != null) {
                if (ServletRequestAWS.getLanguage().equalsIgnoreCase("en")) return gender.getName().getEn();
                return gender.getName().getId();
            }
        } catch (Exception e) {
            System.err.println("Error in getGenderName: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Education Degree">
    public static String SetEducationLevel(EducationDataEmbed education) {
        try {
            if (education != null && education.getDegree() != null) {
                return MultiLangHelper.handle(education.getDegree().getName(), ServletRequestAWS.getLanguage());
            }
        } catch (Exception e) {
            System.err.println("Error in set degree: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Education Institution">
    public static String SetEducationInstitution(EducationDataEmbed education) {
        try {
            if (education != null && education.getInstitution() != null) {
                if (education.getInstitution() instanceof String result) {
                    return result;
                } else if (education.getInstitution() instanceof GeneralDataEmbed result) {
                    return result.getName();
                }
            }
        } catch (Exception e) {
            System.err.println("Error in set institution: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Education Major">
    public static String SetEducationMajor(EducationDataEmbed education) {
        try {
            if (education != null && education.getMajor() != null) {
                if (education.getMajor() instanceof String result) {
                    return result;
                } else if (education.getMajor() instanceof GeneralDataEmbed result) {
                    return result.getName();
                }
            }
        } catch (Exception e) {
            System.err.println("Error in set major: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Last Experience">
    public static String SetExperience(ExperienceDataEmbed experiences) {
        if (experiences == null) return "";
        try {
            return experiences.getPosition();
        } catch (Exception e) {
            System.err.println("Error in getLastExperiencePosition: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Total Experience">
    public static int SetTotalExperience(Integer experiences) {
        if (experiences == null) return 0;
        try {
            return experiences / 12;
        } catch (Exception e) {
            System.err.println("Error in getLastExperiencePosition: " + e.getMessage());
        }
        return 0;
    }
    //</editor-fold>

    //<editor-fold desc="Photo Profile">
    public static String SetPhotoProfile(Object photo) {
        try {
            if (photo == null) return "";

            if (photo instanceof String result) {
                return result;
            } else if (photo instanceof PhotoProfileDataEmbed result) {
                return result.getLink();
            }
        } catch (Exception e) {
            System.err.println("Error in getProfilePhotoLink: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="CV">
    public static String SetCV(Object cv) {
        try {
            if (cv instanceof String result) {
                return result;
            } else if (cv instanceof FileDataEmbed result) {
                return result.getLink();
            }
        } catch (Exception e) {
            System.err.println("Error in getCvLink: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    //<editor-fold desc="Video Resume">
    public static String SetVideoResume(Object videoResume) {
        try {
            if (videoResume == null) return "";
            if (videoResume instanceof String result) {
                return result;
            } else if (videoResume instanceof FileDataEmbed result) {
                return result.getLink();
            }
        } catch (Exception e) {
            System.err.println("Error in getVideoResumeLink: " + e.getMessage());
        }
        return "";
    }
    //</editor-fold>

    public static Integer SetAge(Date data) {
        if (data == null) return 0;
        LocalDate date = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(LocalDate.from(date), LocalDate.now());
        return period.getYears();
    }
}
