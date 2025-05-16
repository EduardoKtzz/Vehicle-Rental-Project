import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.Entities.CarRental;
import model.Entities.Vehicle;
import model.Services.BrazilTaxService;
import model.Services.RentalService;

public class Program {
	public static void main(String[] args) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//Scanner sc
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println();
		
		//Modelo do carro
		System.out.println("Entre com os dados do aluguel:");
		System.out.print("Modelo do carro: ");
		String model = sc.nextLine();

		//Data de inicio do contrato
		System.out.print("Retirada: ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);

		//Data final do contrato
		System.out.print("Retorno:");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		//Instanciando os dados
		CarRental cr = new CarRental(start, finish, new Vehicle(model));

		//Preço do veiculo por hora
		System.out.print("Entre com o preço por hora:");
		Double pricePerHour = sc.nextDouble();

		//Preço do veiculo por dia
		System.out.print("Entre com o preço por dia:");
		Double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processinvoice(cr);

		System.out.println();
		System.out.println("FATURA: ");
		System.out.println("Pagamento básico: " + cr.getInvoice().getBasicPayment());
		System.out.println("Imposto: " + cr.getInvoice().getTax());
		System.out.println("Pagamento total: " + cr.getInvoice().getTotalPayment());

		sc.close();
	}
}
