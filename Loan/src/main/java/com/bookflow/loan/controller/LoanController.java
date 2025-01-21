package com.bookflow.loan.controller;

import com.bookflow.loan.model.entity.Loan;
import com.bookflow.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Obtener todos los préstamos
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    // Obtener un préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.saveLoan(loan);
        return ResponseEntity.ok(savedLoan);
    }

    // Actualizar un préstamo existente
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        try {
            Loan updatedLoan = loanService.updateLoan(id, loanDetails);
            return ResponseEntity.ok(updatedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un préstamo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
