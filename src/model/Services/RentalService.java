package model.Services;

import java.time.Duration;
import model.Entities.CarRental;
import model.Entities.Invoice;

public class RentalService {

	//Attributes
	private Double pricePerHour;
	private Double pricePerDay;

	//Associations
	private TaxServices taxServices;

	//Builders
	public RentalService(Double pricePerHour, Double pricePerDay, TaxServices taxServices) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxServices = taxServices;
	}

	//get e set
	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxServices getTaxServices() {
		return taxServices;
	}

	public void set(BrazilTaxService brazilTaxService) {
		this.taxServices = brazilTaxService;
	}

	public void processinvoice(CarRental carRental) {

		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.0;

		double basicPayment;
		if(hours <= 12.0) {
			basicPayment = pricePerHour * Math.ceil(hours);
		} else {
			basicPayment = pricePerDay * Math.ceil(hours / 24.0);
		}

		double tax = taxServices.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

}
