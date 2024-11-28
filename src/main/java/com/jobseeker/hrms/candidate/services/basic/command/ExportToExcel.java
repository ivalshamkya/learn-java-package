package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.candidate.VideoResumeDataEmbed;
import org.jobseeker.embedded.master.EducationDataEmbed;
import org.jobseeker.embedded.master.ExperienceDataEmbed;
import org.jobseeker.embedded.master.GenderDataEmbed;
import org.jobseeker.embedded.master.PhotoProfileDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.helper.EducationCodeHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("exportToExcel")
public class ExportToExcel {

    private final ICandidateRepository candidateRepository;

    public byte[] execute(Map<Object, Object> request) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String currentDateTime = dateFormatter.format(new Date());

            List<Candidate> data = candidateRepository.findCandidatesExcel(request);

            Sheet sheet = workbook.createSheet("Candidates");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Name", "Email", "Gender", "Province", "City", "Phone", "Birthdate", "Last Education", "Experiences", "Expected Salary", "NIK", "Photo Profile", "CV", "Video Resume"}; // Add more headers as needed
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(prettifyHeader(headers[i]));
            }

            int rowNum = 1;
            for (Candidate candidate : data) {
                Row row = sheet.createRow(rowNum++);
                int cellNum = 0;

                row.createCell(cellNum++).setCellValue(getStringValue(candidate.getName()));
                row.createCell(cellNum++).setCellValue(getStringValue(candidate.getEmail()));
                row.createCell(cellNum++).setCellValue(getGenderName(candidate.getGender()));

                row.createCell(cellNum++).setCellValue(candidate.getProvince() != null ? getStringValue(candidate.getProvince().getName()) : "");
                row.createCell(cellNum++).setCellValue(candidate.getCity() != null ? getStringValue(candidate.getCity().getName()) : "");
                row.createCell(cellNum++).setCellValue(candidate.getPhoneNumber() != null ? getStringValue(candidate.getPhoneNumber().getPhone()) : "");
                row.createCell(cellNum++).setCellValue(candidate.getDob() != null ? candidate.getDob().toString() : "");
                System.out.println("dob >>> " + candidate.getDob().toString());

                row.createCell(cellNum++).setCellValue(candidate.getLastEducation() != null ? getEducationDegree(candidate.getLastEducation()) : "");
                row.createCell(cellNum++).setCellValue(candidate.getExperiences() != null ? getLastExperiencePosition(candidate.getExperiences()) : "");
                row.createCell(cellNum++).setCellValue(getStringValue(candidate.getExpectedSalary()));

                row.createCell(cellNum++).setCellValue(candidate.getIdentityType() != null ? getStringValue(candidate.getIdentityType().getNumber()) : "");
                row.createCell(cellNum++).setCellValue(candidate.getPhotoProfile() != null ? getProfilePhotoLink(candidate.getPhotoProfile()) : "");

                if(candidate.getCv() instanceof FileDataEmbed) {
                    row.createCell(cellNum++).setCellValue(candidate.getCv() != null ? getCvLink((FileDataEmbed) candidate.getCv()) : "");
                } else {
                    row.createCell(cellNum++).setCellValue("");
                }

                row.createCell(cellNum++).setCellValue(candidate.getVideoResume() != null ? getVideoResumeLink(candidate.getVideoResume()) : "");
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private String prettifyHeader(String header) {
        String[] words = header.split("_");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    private String getStringValue(Object obj) {
        try {
            return obj != null ? obj.toString() : "";
        } catch (Exception e) {
            System.err.println("Error in getStringValue: " + e.getMessage());
            return "";
        }
    }

    private String getBirthdate(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        try {
            return localDateTime != null ? localDateTime.toLocalDate().toString() : "";
        } catch (Exception e) {
            System.err.println("Error in getStringValue: " + e.getMessage());
            return "";
        }
    }

    private String getGenderName(GenderDataEmbed gender) {
        try {
            if (gender != null && gender.getName() != null) {
                if(ServletRequestAWS.getLanguage().equalsIgnoreCase("en")) return gender.getName().getEn();
                return gender.getName().getId();
            }
        } catch (Exception e) {
            System.err.println("Error in getGenderName: " + e.getMessage());
        }
        return "";
    }

    private String getEducationDegree(EducationDataEmbed education) {
        try {
            if (education != null && education.getDegree() != null) {
                return EducationCodeHelper.EDUCATION_CODE_EN.get(education.getDegree().get_id());
            }
        } catch (Exception e) {
            System.err.println("Error in getEducationDegree: " + e.getMessage());
        }
        return "";
    }

    private String getLastExperiencePosition(List<ExperienceDataEmbed> experiences) {
        if (experiences == null) return "";

        try {
            if (!experiences.isEmpty() && experiences.getLast().getPosition() != null) {
                return experiences.getLast().getPosition();
            }
        } catch (Exception e) {
            System.err.println("Error in getLastExperiencePosition: " + e.getMessage());
        }

        return "";
    }

    private String getProfilePhotoLink(PhotoProfileDataEmbed photo_profile) {
        try {
            if (photo_profile != null && photo_profile.getLink() != null) {
                return photo_profile.getLink();
            }
        } catch (Exception e) {
            System.err.println("Error in getProfilePhotoLink: " + e.getMessage());
        }
        return "";
    }

    private String getCvLink(FileDataEmbed cv) {
        try {
            return cv != null ? cv.getLink().toString() : "";
        } catch (Exception e) {
            System.err.println("Error in getCvLink: " + e.getMessage());
            return "";
        }
    }

    private String getVideoResumeLink(VideoResumeDataEmbed video_resume) {
        try {
            if (video_resume != null && video_resume.getLink() != null) {
                return video_resume.getLink();
            }
        } catch (Exception e) {
            System.err.println("Error in getVideoResumeLink: " + e.getMessage());
        }
        return "";
    }
}
