import java.io.IOException;
import java.net.http.HttpClient;

public class Main{
    private static final String COMPANIES_URL = "https://fake-json-api.mock.beeceptor.com/companies";
    private static final String USERS_URL = "https://fake-json-api.mock.beeceptor.com/users";
    public static void main(String[] args){
        try{
            HttpClient client=HttpClient.newHttpClient();
            // Получение и обработка данных компаний
            String companiesResponse=JsonProcessor.fetchData(client,COMPANIES_URL);
            System.out.println("Companies Response:");
            JsonProcessor.process(companiesResponse);
            // Получение и обработка данных пользователей
            String usersResponse=JsonProcessor.fetchData(client,USERS_URL);
            System.out.println("\nUsers Response:");
            JsonProcessor.process(usersResponse);
        } 
        catch(Exception e){
            System.err.println(e);
        }
    }
}
