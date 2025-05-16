package model.Entities;

public class Invoice {

	//Attributes
	private Double basicPayment;
	private Double tax;

	//Builders
	public Invoice() {

	}

	public Invoice(Double basicPayment, Double tax) {
		this.basicPayment = basicPayment;
		this.tax = tax;
	}

	//Get e set
	public Double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(Double basicPayment) {
		this.basicPayment = basicPayment;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;

	}

	public Double getTotalPayment() {
		return getBasicPayment() + getTax();
	}

}
