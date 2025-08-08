package sdd.bni.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static boolean isDateChange(String excelDateStr, Date dbDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate excelDate = LocalDate.parse(excelDateStr, formatter);

        LocalDate entityDate = dbDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return !excelDate.equals(entityDate);
    }

    public static LocalDate convertExcelDate (String excelDateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate excelDate = LocalDate.parse(excelDateStr, formatter);

        return excelDate;
    }

    public static LocalDate convertDatetoLocalDate (Date date){

        if (date == null) {
            return null; 
        }
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else if (date instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) date).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } else {
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

}
