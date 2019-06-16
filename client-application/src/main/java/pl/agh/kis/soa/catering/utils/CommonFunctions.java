package pl.agh.kis.soa.catering.utils;

public class CommonFunctions {

    public static void initDB() {
//        InitDatabase.getInstance();
//        InitDatabase initDatabase = new InitDatabase();
    }

    public static String redirectToPage(String pageName) {
        return "/" + pageName + ".xhtml?faces-redirect=true";
    }

}
