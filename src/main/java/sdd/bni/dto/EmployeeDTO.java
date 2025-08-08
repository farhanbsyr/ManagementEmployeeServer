package sdd.bni.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusDataEmployee;
import sdd.bni.enums.StatusEmployee;

public class EmployeeDTO {

    public EmployeeDTO(){}
    
    public EmployeeDTO(String id, String name, GenderEnum gender, StatusEmployee status, String position, String branch,
            LocalDate awalKontrak, LocalDate akhirKontrak) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.position = position;
        this.branch = branch;
        this.awalKontrak = awalKontrak;
        this.akhirKontrak = akhirKontrak;
    }
    private String id;
    private String name;
    private GenderEnum gender;
    private StatusEmployee status;
    private String position;
    private String branch;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate awalKontrak;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate akhirKontrak;
    private StatusDataEmployee statusDateEmployee;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public GenderEnum getGender() {
        return gender;
    }
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    public StatusEmployee getStatus() {
        return status;
    }
    public void setStatus(StatusEmployee status) {
        this.status = status;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public LocalDate getAwalKontrak() {
        return awalKontrak;
    }
    public void setAwalKontrak(LocalDate awalKontrak) {
        this.awalKontrak = awalKontrak;
    }
    public LocalDate getAkhirKontrak() {
        return akhirKontrak;
    }
    public void setAkhirKontrak(LocalDate akhirKontrak) {
        this.akhirKontrak = akhirKontrak;
    }
    public StatusDataEmployee getStatusDateEmployee() {
        return statusDateEmployee;
    }
    public void setStatusDateEmployee(StatusDataEmployee statusDateEmployee) {
        this.statusDateEmployee = statusDateEmployee;
    }
    
    
}
