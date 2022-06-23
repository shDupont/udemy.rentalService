import entities.CarRental;
import entities.Vehicle;
import jdk.swing.interop.SwingInterOpUtils;
import services.BrazilTaxService;
import services.RentalService;

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        System.out.println("Enter rental data");

            System.out.println("Car model:");
            String carModel = sc.nextLine();
            System.out.print("Pickup: ");
            Date datePickup = sdf.parse(sc.nextLine());
            System.out.print("Return: ");
            Date dateReturn = sdf.parse(sc.nextLine());
            System.out.print("Price per hour: ");
            Double pricePerHour = sc.nextDouble();
            System.out.print("Price per day: ");
            Double pricePerDay = sc.nextDouble();

            CarRental cr = new CarRental(datePickup, dateReturn, new Vehicle(carModel));
            RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

            rentalService.processInvoice(cr);

            System.out.println("Invoice: ");
            System.out.println("Basic payment: " + cr.getInvoice().getBasicPayment());
            System.out.println("Tax: " + cr.getInvoice().getTax());
            System.out.println("Total payment: " + cr.getInvoice().totalPayment());



    }
}