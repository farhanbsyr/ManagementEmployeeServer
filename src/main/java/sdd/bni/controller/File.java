package sdd.bni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sdd.bni.dto.EmployeeDTO;
import sdd.bni.service.FileService;

@RestController
public class File {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadExcel (@RequestParam MultipartFile excel){
         if (excel.isEmpty()) return ResponseEntity.badRequest().body("File kosong");
         List<EmployeeDTO> response = fileService.convertExcelToObject(excel);
         return ResponseEntity.ok().body(response);
    }
}
