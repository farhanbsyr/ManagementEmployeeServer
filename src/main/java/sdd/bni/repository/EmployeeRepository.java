package sdd.bni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sdd.bni.enums.GenderEnum;
import sdd.bni.enums.StatusEmployee;
import sdd.bni.modal.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

    @Query(value = """
    SELECT 
    e.*, 
    p.position_name, 
    b.branch_name
FROM employee e
JOIN position p ON e.id_position = p.id
JOIN branch b ON e.id_branch = b.id
WHERE 
    (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND (:#{#positions == null || #positions.isEmpty()} = true OR p.position_name IN (:positions))
    AND (:#{#branches == null || #branches.isEmpty()} = true OR b.branch_name IN (:branches))
    AND (:#{#genders == null || #genders.isEmpty()} = true OR e.gender IN (:genders))
    AND (:#{#statuses == null || #statuses.isEmpty()} = true OR e.status IN (:statuses))

    """,
    nativeQuery = true)
List<Employee> filterEmployee(
    @Param("name") String name,
    @Param("positions") List<String> positions,
    @Param("branches") List<String> branches,
    @Param("genders") List<GenderEnum> genders,
    @Param("statuses") List<StatusEmployee> statuses
);

}
