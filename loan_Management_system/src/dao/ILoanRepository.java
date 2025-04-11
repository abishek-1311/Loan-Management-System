package dao;

import java.util.List;

import entities.*;
import exception.InvalidLoanException;

public interface ILoanRepository {
	void applyLoan(Loan loan);
	double calculateInterest(int loanId) throws InvalidLoanException;
	void loanStatus(int loanId);
	double calculateEMI(int loanId) throws InvalidLoanException;
	void loanRepayment(int loanId, double amount);
	List<Loan> getAllLoan();
	Loan getLoanById(int loanId) throws InvalidLoanException;
}
