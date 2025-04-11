package client;

import java.util.List;
import java.util.Scanner;

import dao.ILoanRepositoryImpl;
import entities.Loan;

public class Demo {
	public static void main(String[] args) {
		ILoanRepositoryImpl loanrep  = new ILoanRepositoryImpl();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("****Menu****");
			System.out.println("1.Apply Loan.\n2.Get Status\n3.Get All Loan.\n4.Get Loan.\n5.Loan Repayment");
			System.out.print("Enter your choice :");
			int choice  = sc.nextInt();
			switch(choice) {
			case 1:{
				Loan loan = new Loan();
				System.out.print("Enter loan Id");
				loan.setLoanId(sc.nextInt());
				System.out.print("Enter customer Id :");
				loan.setCustomerID(sc.nextInt());
				System.out.print("Enter Amount needed:");
				loan.setPrincipalAmount(sc.nextDouble());
				System.out.print("Enter loan term :");
				loan.setLoanTerm(sc.nextInt());
				sc.nextLine();
				System.out.println("Enter loan Type(HomeLoan or CarLoan) :");
				loan.setLoanType(sc.nextLine());
				System.out.println();
				
				loanrep.applyLoan(loan);
				break;
			}
			
			case 2 :{
				System.out.print("Enter the Loan Id :");
				loanrep.loanStatus(sc.nextInt());
				break;
			}
			
			case 3:{
				List<Loan> list = loanrep.getAllLoan();
				
				for(Loan l : list) {
					System.out.println(l.toString());
				}
				break;
			}
			case 4:{
				try {
					System.out.print("Enter the Loan Id :");
					loanrep.getLoanById(sc.nextInt());
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
				break;
			}
			case 5:{
				System.out.print("Enter the loan_id :");
				int loanId = sc.nextInt();
				System.out.print("Enter amount to PAID :");
				double amount  = sc.nextDouble();
				
				loanrep.loanRepayment(loanId, amount);
				break;
			}
			case 6:{
				System.out.println("System Shutting Down");
				System.exit(0);
			}
			default :{
				System.out.println("Enter valid choice...!");
			}
			
			}
		}
	}
}
