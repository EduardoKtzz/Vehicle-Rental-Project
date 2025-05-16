package model.Services;

import java.time.Duration;

import model.Entities.CarRental;
import model.Entities.Invoice;

public class RentalService {

	//Attributes
	private Double pricePerHour;
	private Double pricePerDay;

	//Associations
	private BrazilTaxService brazilTaxService;
	private CarRental carRental;

	//Builders
	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService brazilTaxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.brazilTaxService = brazilTaxService;
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

	public BrazilTaxService getBrazilTaxService() {
		return brazilTaxService;
	}

	public void setBrazilTaxService(BrazilTaxService brazilTaxService) {
		this.brazilTaxService = brazilTaxService;
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

		double tax = brazilTaxService.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

	public CarRental getCarRental() {
		return carRental;
	}

	public void setCarRental(CarRental carRental) {
		this.carRental = carRental;
	}

}
