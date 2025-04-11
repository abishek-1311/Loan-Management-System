package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import entities.Loan;
import exception.InvalidLoanException;
import util.DBConnUtil;

public class ILoanRepositoryImpl implements ILoanRepository {

	public static Connection con;
	
	public ILoanRepositoryImpl() {
		try {
			con = DBConnUtil.getConnection();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void applyLoan(Loan loan) {
		Scanner sc = new Scanner(System.in);
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into loan(loan_id ,customer_id,pricipal_amt,interest_rate,loan_term,loan_type) values(?,?,?,?,?,?)");
			stmt.setInt(1, loan.getLoanId());
			stmt.setInt(2, loan.getCustomerID());
			stmt.setDouble(3, loan.getPrincipalAmount());
			stmt.setDouble(4, loan.getLoanType().equals("HomeLoan") ? 0.08 : 0.1);
			stmt.setInt(5, loan.getLoanTerm());
			stmt.setString(6, loan.getLoanType());
			System.out.print("Enter 1 to confirm :");
			int confirm = sc.nextInt();
			if(confirm==1) {
				int res = stmt.executeUpdate();
				if(res>=1)
					System.out.println("Loan applied successfully..!");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public double calculateInterest(int loanId) throws InvalidLoanException {
		double interest =0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Loan where loan_id ="+loanId);
			if(rs.next()) {
				interest = calculateInterest(rs.getDouble(3),rs.getDouble(4), rs.getInt(5));
			}
			else {
				throw new InvalidLoanException("Loan Id :"+loanId+" Not Found.");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return Math.floor(interest);
	}
	
	public double calculateInterest(double amount,double rate,int term ) {
		double interest;
		interest = (amount *  rate * term) / 12 ;
		return interest;
	}

	@Override
	public void loanStatus(int loanId) {
		ILoanRepositoryImpl loan = new ILoanRepositoryImpl();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select credit_score from customer where customer_id =(select customer_id from loan where loan_id = "+loanId+")");
			rs.next();
			int creditScore = rs.getInt(1);
			
			if(creditScore > 650) {
				Statement st2 = con.createStatement();
				st2.executeUpdate("update loan set loan_status = 'Approved' where loan_id ="+loanId);
				System.out.println("Loan Approved...!");
			}
			else {
				Statement st3 = con.createStatement();
				st3.executeUpdate("update loan set loan_status = 'Rejected' where loan_id ="+loanId);
				System.out.println("Sorry your Loan Rejected...!");
				
			}
			
			//cal no of emi.
			Statement stmt2  = con.createStatement();
			ResultSet rset= stmt2.executeQuery("select loan_term from loan where loan_id ="+loanId);
			rset.next();
			double emi = loan.calculateEMI(loanId);
			int loanTerm = rset.getInt(1);
			double totalAmount = emi * loanTerm;  
			int noOfEmi = (int)(totalAmount / emi);
			
			//emi count
			Statement stmt3 = con.createStatement();
			stmt3.executeUpdate("update loan set emipaidcount ="+noOfEmi+" where loan_id = "+loanId);
			System.out.println("You have to pay "+emi+" in "+noOfEmi+" Emi's.");
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public double calculateEMI(int loanId) throws InvalidLoanException {
		
		double emi =0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Loan where loan_id ="+loanId);
			if(rs.next()) {
				emi = calculateEMI(rs.getDouble(3),rs.getDouble(4), rs.getInt(5));
			}
			else {
				throw new InvalidLoanException("Loan Id :"+loanId+" Not Found.");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return Math.floor(emi);
	}
	
	public double calculateEMI(double amount,double rate,int term ) {
		double EMI = 0;
		double P = amount ;
		double R = rate/12/100;
		int  N = term;
		
		double numerator = amount * rate * Math.pow(1 + rate, term);  //EMI = [P * R * (1+R)^N] / [(1+R)^N-1] 
				
		double denominator = Math.pow(1 + rate, term) - 1;
		
		EMI = numerator / denominator;
		
		return EMI;
	}

	@Override
	public void loanRepayment(int loanId, double amount) {
		ILoanRepositoryImpl loan = new ILoanRepositoryImpl();
		
		try {
			double calEmi = loan.calculateEMI(loanId); 
			if(amount==calEmi) {
				Statement stmt = con.createStatement();
				ResultSet set = stmt.executeQuery("select emipaidcount from loan where loan_id="+loanId);
				set.next();
				int newEmiPaidCount = set.getInt(1)-1;
				if(newEmiPaidCount == 0) {
					System.out.println("Loan closed.");
				}
				else { // update new paid count.
					Statement stmt2 = con.createStatement();
					stmt2.executeUpdate("update loan set emipaidcount="+newEmiPaidCount+" where loan_id="+loanId);
					System.out.println("Amount paid successfully. need to pay "+newEmiPaidCount+" Emi's");
				}
				
			}
			else {
				System.out.println("Amount Insufficient.");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
		

	@Override
	public List<Loan> getAllLoan() {
		List<Loan> list= new ArrayList<Loan>();
		
		try {
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery("select * from loan");
			while(set.next()) {
				Loan loan = new Loan();
				loan.setLoanId(set.getInt(1));
				loan.setCustomerID(set.getInt(2));
				loan.setPrincipalAmount(set.getDouble(3));
				loan.setInterestRate(set.getDouble(4));
				loan.setLoanTerm(set.getInt(5));
				loan.setLoanType(set.getString(6));
				loan.setLoanStatus(set.getString(7));
				loan.setEmiPaidCount(set.getInt(8));
				list.add(loan);
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public Loan getLoanById(int loanId) throws InvalidLoanException {
		Loan loan = new Loan() ; 
		try {
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery("select * from loan where loan_id ="+loanId);
			if(set.next()) {
				loan.setLoanId(set.getInt(1));
				loan.setCustomerID(set.getInt(2));
				loan.setPrincipalAmount(set.getDouble(3));
				loan.setInterestRate(set.getDouble(4));
				loan.setLoanTerm(set.getInt(5));
				loan.setLoanType(set.getString(6));
				loan.setLoanStatus(set.getString(7));
				loan.setEmiPaidCount(set.getInt(8));
			}
			else {
				throw new InvalidLoanException("Loan Id :"+loanId+" Not found.");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return loan;
	}

}
