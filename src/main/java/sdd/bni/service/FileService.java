package sdd.bni.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sdd.bni.dto.EmployeeDTO;
import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusEmployee;
import sdd.bni.exception.DataNotFoundException;
import sdd.bni.exception.InvalidExcelFileException;
import sdd.bni.utility.DateUtils;

@Service
public class FileService {

    @Autowired
    private EmployeeService employeeService;

    public boolean checkExcel(MultipartFile excel){
        String contentType = excel.getContentType();

        if (contentType == null) {
            return false;
        }

        if (!contentType.equals("application/vnd.ms-excel") &&
            !contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return false;
        }

        return true;
    }

    public List<EmployeeDTO> convertExcelToObject (MultipartFile excel){
        String fileName = excel.getOriginalFilename();
       
        if (fileName == null || (!fileName.endsWith("xlsx") && !fileName.endsWith("xls"))) {
            throw new InvalidExcelFileException("Please upload file excel");
        }

        
        List<EmployeeDTO> dataExcel = getDataExcle(excel, fileName);

        if (dataExcel == null) {
            throw new DataNotFoundException("Excel doesn't have any row");
        }

        List<EmployeeDTO> response = employeeService.checkedDataEmployee(dataExcel);

        return response;
    }

    public List<EmployeeDTO> getDataExcle(MultipartFile excel, String fileName){
        List<EmployeeDTO> response = new ArrayList<>();
        try (InputStream is = excel.getInputStream(); Workbook workbook = 
            fileName.endsWith(".xlsx") ? new XSSFWorkbook(is) : new HSSFWorkbook(is)) {
    
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                
                if (row == null) continue;

                 // Ambil sel pertama (biasanya kolom ID)
                Cell firstCell = row.getCell(0);
                String idValue = getCellValue(firstCell);

                // Kalau kolom ID kosong/null, kita anggap akhir data → hentikan loop
                if (idValue == null || idValue.trim().isEmpty()) {
                    break;
                }

                EmployeeDTO rowData = new EmployeeDTO();

                rowData.setId(getCellValue(row.getCell(0)));
                rowData.setName(getCellValue(row.getCell(1)));
                rowData.setGender(GenderEnum.valueOf(getCellValue(row.getCell(2)).toUpperCase()));
                rowData.setStatus(StatusEmployee.valueOf(getCellValue(row.getCell(3))));
                rowData.setPosition(getCellValue(row.getCell(4)));
                rowData.setBranch(getCellValue(row.getCell(5)));
                rowData.setAwalKontrak(DateUtils.convertExcelDate(getCellValue(row.getCell(6))));
                rowData.setAkhirKontrak(DateUtils.convertExcelDate(getCellValue(row.getCell(7))));
            
                response.add(rowData);
            }

            return response;
        } catch (Exception e) {
            throw new InvalidExcelFileException("Please upload file excel");
        }
    }

    public String getCellValue(Cell cell){
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING: 
                    return cell.getStringCellValue();
            case NUMERIC: 
                   if (DateUtil.isCellDateFormatted(cell)) {
                        return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                    } else {
                        return String.valueOf((long) cell.getNumericCellValue());
                    }
            case BOOLEAN: 
                    return String.valueOf(cell.getBooleanCellValue());  
            default: return "";
        }
    }
}
