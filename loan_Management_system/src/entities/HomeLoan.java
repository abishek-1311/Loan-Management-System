package entities;

public class HomeLoan extends Loan {
	private String propertyAddress;
	private double  propertyValue;
	
	public HomeLoan() {
		
	}
	
	public HomeLoan(String propertyAddress, double propertyValue) {
		super();
		this.propertyAddress = propertyAddress;
		this.propertyValue = propertyValue;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "HomeLoan [propertyAddress=" + propertyAddress + ", propertyValue=" + propertyValue + "]";
	}
	
	
}
