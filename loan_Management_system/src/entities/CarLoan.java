package entities;

public class CarLoan extends Loan{
	private String carModel;
	private double carValue;
	
	public CarLoan() {
		
	}
	
	public CarLoan(String carModel, double carValue) {
		super();
		this.carModel = carModel;
		this.carValue = carValue;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public double getCarValue() {
		return carValue;
	}
	public void setCarValue(double carValue) {
		this.carValue = carValue;
	}
	
	
}
