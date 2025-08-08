package sdd.bni.modal;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusEmployee;

@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String email;
    private String noHandphone;
    @Enumerated(EnumType.STRING)
    private StatusEmployee status;
    private String address;

    @Temporal(TemporalType.DATE)
    private Date awalKontrak;
    @Temporal(TemporalType.DATE)
    private Date akhirkontrak;
    @Temporal(TemporalType.DATE)
    private Date awalMasuk;
  
    private Date updateAt;
    private Long idPosition;
    private Long idBranch;
    
    @ManyToOne
    @JoinColumn(name = "idPosition" , insertable = false, updatable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "idBranch" , insertable = false, updatable = false)
    private Branch branch;

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getNoHandphone() {
        return noHandphone;
    }
    public Long getIdPosition() {
        return idPosition;
    }
    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }
    public Long getIdBranch() {
        return idBranch;
    }
    public void setIdBranch(Long idBranch) {
        this.idBranch = idBranch;
    }
    public void setNoHandphone(String noHandphone) {
        this.noHandphone = noHandphone;
    }
    public Date getAwalKontrak() {
        return awalKontrak;
    }
    public void setAwalKontrak(Date awalKontrak) {
        this.awalKontrak = awalKontrak;
    }
    public Date getAkhirkontrak() {
        return akhirkontrak;
    }
    public void setAkhirkontrak(Date akhirkontrak) {
        this.akhirkontrak = akhirkontrak;
    }
    public Date getAwalMasuk() {
        return awalMasuk;
    }
    public void setAwalMasuk(Date awalMasuk) {
        this.awalMasuk = awalMasuk;
    }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
   
   


}
