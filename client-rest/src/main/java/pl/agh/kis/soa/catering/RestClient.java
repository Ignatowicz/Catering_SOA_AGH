package pl.agh.kis.soa.catering;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class RestClient {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int option = 1;
        Long menuCategoryId;

        while(option != 0) {
            System.out.println("1: wszystkie kategorie jako json");
            System.out.println("2: wszystkie kategorie jako xml");
            System.out.println("3: wybrana kategoria jako json");
            System.out.println("4: wybrana kategoria jako xml");
            System.out.print("0: wyjście z programu \n>> ");

            option = reader.nextInt();
            switch(option) {
                case 1:
                    getAllMenuCategoriesAsJson();
                    break;
                case 2:
                    getAllMenuCategoriesAsXml();
                    break;
                case 3:
                    System.out.print("Proszę podać ID kategorii: ");
                    menuCategoryId = reader.nextLong();
                    getMenuCategoryByIdAsJson(menuCategoryId);
                    break;
                case 4:
                    System.out.print("Proszę podać ID kategorii: ");
                    menuCategoryId = reader.nextLong();
                    getMenuCategoryByIdAsXml(menuCategoryId);
                    break;
            }
        }
    }

    private static void getAllMenuCategoriesAsJson() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category");
        Response response = target.request()
                .header("Accept-Language", "en")
                .accept(MediaType.APPLICATION_JSON).get();
        printResponse(response);
    }

    private static void getAllMenuCategoriesAsXml() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category");
        Response response = target.request().accept(MediaType.APPLICATION_XML).get();
        printResponse(response);
    }

    private static void getMenuCategoryByIdAsJson(Long categoryId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category/" + categoryId);
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        printResponse(response);
    }

    private static void getMenuCategoryByIdAsXml(Long categoryId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest/api/category/" + categoryId);
        Response response = target.request().accept(MediaType.APPLICATION_XML).get();
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
