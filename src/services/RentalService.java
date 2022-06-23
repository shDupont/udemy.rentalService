package services;

import entities.CarRental;
import entities.Invoice;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;
    private TaxService taxService;

    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental){
        long t1 = carRental.getStart().getTime();
        long t2 = carRental.getFinish().getTime();

        double basicPayment;
        Double duration = (double) (t2-t1) / 1000 / 60 / 60;
        if(duration <= 12.0){
            basicPayment = Math.ceil(duration) * pricePerHour;
        }
        else {
            basicPayment = Math.ceil(duration / 24) * pricePerDay;
        }

        double tax = taxService.tax(basicPayment);
        carRental.setInvoice(new Invoice(tax, basicPayment));
    }
}
