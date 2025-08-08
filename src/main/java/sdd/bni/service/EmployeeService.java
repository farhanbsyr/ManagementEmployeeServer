package sdd.bni.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdd.bni.dto.EmployeeDTO;
import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusDataEmployee;
import sdd.bni.enums.StatusEmployee;
import sdd.bni.modal.Branch;
import sdd.bni.modal.Employee;
import sdd.bni.modal.Position;
import sdd.bni.repository.BranchRepository;
import sdd.bni.repository.EmployeeRepository;
import sdd.bni.repository.PositionRepository;
import sdd.bni.utility.DateUtils;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private BranchRepository branchRepository;

    public List<EmployeeDTO> checkedDataEmployee (List<EmployeeDTO> data){
        ArrayList<EmployeeDTO> response = new ArrayList<>();

        for (EmployeeDTO cell : data) {
            Employee employee = employeeRepository.findById(Long.parseLong(cell.getId())).orElse(null);
            StatusDataEmployee statusDataEmployee = StatusDataEmployee.UNCHANGED;

            if (employee == null) {
                statusDataEmployee = StatusDataEmployee.NOT_FOUND;
            } else if (!employee.getName().equals(cell.getName()) || 
                !employee.getGender().equals(cell.getGender()) ||
                !employee.getPosition().getPositionName().equals(cell.getPosition()) ||
                !employee.getBranch().getBranchName().equals(cell.getBranch()) ||
                !DateUtils.convertDatetoLocalDate(employee.getAwalKontrak()).equals(cell.getAwalKontrak())||
                !DateUtils.convertDatetoLocalDate(employee.getAkhirkontrak()).equals(cell.getAkhirKontrak())
                // DateUtils.isDateChange(cell.get("awalKontrak"), employee.getAwalKontrak()) ||
                // DateUtils.isDateChange(cell.get("akhirKontrak"), employee.getAkhirkontrak())
            ) {
                statusDataEmployee = StatusDataEmployee.CHANGED;
            }  

            cell.setStatusDateEmployee(statusDataEmployee);
            response.add(cell);
        }

        return response;
    }

    public List<EmployeeDTO> getAllEmployees (){
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId(String.valueOf(employee.getId()));
            employeeDTO.setName(employee.getName());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setStatus(employee.getStatus());
            employeeDTO.setPosition(employee.getPosition().getPositionName());
            employeeDTO.setBranch(employee.getBranch().getBranchName());
            employeeDTO.setAwalKontrak(DateUtils.convertDatetoLocalDate(employee.getAwalKontrak()));
            employeeDTO.setAkhirKontrak(DateUtils.convertDatetoLocalDate(employee.getAkhirkontrak()));

            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }


    public boolean createEmployee(Employee employee) {
        employee.setAwalMasuk(new Date());
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> filterEmployee(String name, List<String> position, List<String> branch, List<GenderEnum> gender, List<StatusEmployee> status) {
        System.out.println(gender);
        System.out.println(branch);
        System.out.println(position);
        List<Employee> filteredEmployees= employeeRepository.filterEmployee(name, position, branch, gender, status);
        return filteredEmployees;
    }

    public Map<String, Object> typeFilter() {
        Map<String, Object> response = new HashMap<>();

        List<Position> positions = positionRepository.findAll();
        List<Branch> branchs = branchRepository.findAll();
        
        List<GenderEnum> genders = List.of(GenderEnum.MALE, GenderEnum.WOMAN);
        List<StatusEmployee> statusEmployees = List.of(StatusEmployee.ACTIVE, StatusEmployee.INACTIVE, StatusEmployee.EXPIRING);

        response.put("positions", positions);
        response.put("branchs", branchs);
        response.put("status", statusEmployees);
        response.put("genders", genders);
        
        return response;
    }
}
