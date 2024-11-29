package com.jobseeker.hrms.candidate.helper;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ImportHelper {
    //<editor-fold defaultstate="collapsed" desc="parseDate">
    public static Date parseDate(String stringDate) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
        }catch (ParseException e) {
            System.err.println("Error in parseDate: " + e.getMessage());
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="converRowTowString">
    public static String convertCellToString(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    yield cell.getLocalDateTimeCellValue().format(formatter);
                }
                yield Integer.toString((int) cell.getNumericCellValue());
            }
            default -> null;
        };
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="convertCellFloatToString">
    public static String convertCellFloatToString(Cell cell) {
        if (cell == null) {
            return "";
        }else{
            return String.valueOf(cell.getNumericCellValue());
        }
    }
    //</editor-fold>

    //<editor-fold desc="getCurrentDateTime">
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    //</editor-fold>
}
