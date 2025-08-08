package sdd.bni.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdd.bni.dto.EmployeeDTO;
import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusEmployee;
import sdd.bni.modal.Employee;
import sdd.bni.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> allDataEmployee () {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping("employee")
    public ResponseEntity<Object> createEmployee (@RequestBody Employee employee){
        boolean isCreated = employeeService.createEmployee(employee);

        if (!isCreated) {
            return ResponseEntity.badRequest().body("ada yang salah");
        }
        
        return ResponseEntity.status(201).body(isCreated);
    }

    @GetMapping("searchEmployee")
    public ResponseEntity<List<Employee>> searchEmployee (
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "gender", required = false) List<GenderEnum> gender, 
        @RequestParam(value = "position", required = false) List<String> position,
        @RequestParam(value ="status", required = false) List<StatusEmployee> status,
        @RequestParam(value = "branch", required = false) List<String> branch 
    ){

            if (position != null && position.isEmpty()) position = null;
    if (branch != null && branch.isEmpty()) branch = null;
    if (gender != null && gender.isEmpty()) gender = null;
    if (status != null && status.isEmpty()) status = null;
        List<Employee> filteredEmployees = employeeService.filterEmployee(name, position, branch,  gender,  status);

        return ResponseEntity.ok().body(filteredEmployees);
    }

    @GetMapping("typeFilter")
    public ResponseEntity<Map<String, Object>> allTypeFilter (){
        Map<String,Object> types = employeeService.typeFilter();
        return ResponseEntity.ok().body(types);
    }


}
