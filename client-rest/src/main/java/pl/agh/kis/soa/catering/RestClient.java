package pl.agh.kis.soa.catering;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;


public class RestClient {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int option = 1;
        Long categoryId;

        while (option != 0) {
            System.out.print("Język: [pl/en] \n>> ");
            String language = reader.next();

            System.out.println("1: wszystkie kategorie jako xml");
            System.out.println("2: wszystkie kategorie jako json");
            System.out.println("3: wybrana kategoria jako xml");
            System.out.println("4: wybrana kategoria jako json");
            System.out.print("0: wyjście z programu \n>> ");

            option = reader.nextInt();
            switch (option) {
                case 1:
                    getCategories(language, MediaType.APPLICATION_XML);
                    break;
                case 2:
                    getCategories(language, MediaType.APPLICATION_JSON);
                    break;
                case 3:
                    System.out.print("Proszę podać ID kategorii: ");
                    categoryId = reader.nextLong();
                    getCategory(categoryId, language, MediaType.APPLICATION_XML);
                    break;
                case 4:
                    System.out.print("Proszę podać ID kategorii: ");
                    categoryId = reader.nextLong();
                    getCategory(categoryId, language, MediaType.APPLICATION_JSON);
                    break;
            }
        }
    }

    private static void getCategory(Long categoryId, String language, String mediaType) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category/" + categoryId);
        Response response = target
                .register(new BasicAuthentication("manager", "manager"))
                .request()
                .accept(mediaType)
                .header("Accept-Language", language)
                .get();
        printResponse(response);
    }

    private static void getCategories(String language, String mediaType) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category");
        Response response = target
                .register(new BasicAuthentication("manager", "manager"))
                .request()
                .accept(mediaType)
                .header("Accept-Language", language)
                .get();
        printResponse(response);
    }

    private static void printResponse(Response response) {
        System.out.println("\nStatus: " + response.getStatus());
        System.out.println("Odpowiedź:");
        String value = response.readEntity(String.class);
        System.out.println(value + "\n");
        response.close();
    }

}
