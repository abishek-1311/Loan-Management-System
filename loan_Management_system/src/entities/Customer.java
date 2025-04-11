package entities;

public class Customer {
	private int  CustomerID;
	private String Name;
	private String Email ;
	private  int Phone;
	private String Address;
	private int creditScore;
	
	public Customer() {
		
	}
	
	public Customer(int customerID, String name, String email, int phone, String address, int creditScore) {
		super();
		CustomerID = customerID;
		Name = name;
		Email = email;
		Phone = phone;
		Address = address;
		this.creditScore = creditScore;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "Customer [CustomerID=" + CustomerID + ", Name=" + Name + ", Email=" + Email + ", Phone=" + Phone
				+ ", Address=" + Address + ", creditScore=" + creditScore + "]";
	}
	
	
}
