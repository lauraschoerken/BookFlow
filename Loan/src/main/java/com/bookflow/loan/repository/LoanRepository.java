package com.bookflow.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookflow.loan.model.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
