package sdd.bni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sdd.bni.modal.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
