package com.bookflow.loan.service;

import com.bookflow.loan.model.entity.Loan;
import com.bookflow.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Obtener todos los préstamos
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Obtener un préstamo por ID
    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    // Guardar un nuevo préstamo
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    // Actualizar un préstamo existente
    public Loan updateLoan(Long id, Loan loanDetails) {
        return loanRepository.findById(id).map(loan -> {
            loan.setUserId(loanDetails.getUserId());
            loan.setBookId(loanDetails.getBookId());
            loan.setStartDate(loanDetails.getStartDate());
            loan.setEndDate(loanDetails.getEndDate());
            loan.setReturnDate(loanDetails.getReturnDate());
            return loanRepository.save(loan);
        }).orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    // Eliminar un préstamo por ID
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
