package com.bookflow.loan.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
    private Long userId; 
    private Long bookId; 

    private LocalDate startDate; 
    private LocalDate endDate; 
    private LocalDate returnDate;
}
