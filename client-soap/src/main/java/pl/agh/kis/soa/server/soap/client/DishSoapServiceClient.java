package pl.agh.kis.soa.server.soap.client;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import pl.agh.kis.soa.server.soap.implementations.DishSoapService;
import pl.agh.kis.soa.server.soap.implementations.DishSoapServiceImplService;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DishSoapServiceClient {

    public static void main(String[] args) {
        DishSoapServiceImplService dishSoapServiceImplService = new DishSoapServiceImplService();
        DishSoapService dishSoapService = dishSoapServiceImplService.getDishSoapServiceImplPort();

        ioLoop("t", new Scanner(System.in), dishSoapService);
    }


    private static void ioLoop(String response, Scanner scanner, DishSoapService dishSoapService) {
        if (response.toLowerCase().equals("t")) {
            System.out.print("Dodać danie do menu? [T/N]\n>> ");

            try {
                response = scanner.next();

                if (response.toLowerCase().equals("t")) {
                    System.out.print("Nazwa dania: ");
                    String name = scanner.next();

                    System.out.print("Cena dania: ");
                    Float price = scanner.nextFloat();

                    System.out.print("ID kategorii dania: ");
                    Long categoryId = scanner.nextLong();

                    dishSoapService.addDishToCategory(name, price, categoryId);
                }

            } catch (ServerSOAPFaultException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawny format danych wejściowych!");
            } finally {
                ioLoop(response, scanner, dishSoapService);
            }
        }
    }

}