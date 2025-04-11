package entities;

public class Loan extends Customer {
	private int loanId;
	private int customerID;
	private double principalAmount;
	private double interestRate;
	private int loanTerm ;
	private String loanType; 
	private String loanStatus ;
	private int emiPaidCount;
	
	

	public Loan() {
		
	}

	public Loan(int loanId, int customerID, double principalAmount, double interestRate, int loanTerm, String loanType,
			String loanStatus) {
		super();
		this.loanId = loanId;
		this.customerID = customerID;
		this.principalAmount = principalAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}
	
	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	public int getEmiPaidCount() {
		return emiPaidCount;
	}

	public void setEmiPaidCount(int emiPaidCount) {
		this.emiPaidCount = emiPaidCount;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", customerID=" + customerID + ", principalAmount=" + principalAmount
				+ ", interestRate=" + interestRate + ", loanTerm=" + loanTerm + ", loanType=" + loanType
				+ ", loanStatus=" + loanStatus + ", emiPaidCount=" + emiPaidCount + "]";
	}

}
