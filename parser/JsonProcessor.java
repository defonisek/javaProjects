import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonProcessor{
    public static String fetchData(HttpClient client,String url) throws Exception{
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if(response.statusCode()==200)
            return response.body(); 
        else
            throw new RuntimeException("Failed to fetch data: " + response.statusCode());
    }
    // Функция для разбора JSON и вывода без фигурных скобок
    public static void process(String json){
        if(json.trim().startsWith("[")){
            JSONArray jsonArray=new JSONArray(json);
            for(int i=0;i<jsonArray.length();++i)
                printJsonObject(jsonArray.getJSONObject(i));
        } 
        else{
            JSONObject jsonObject=new JSONObject(json);
            printJsonObject(jsonObject);
        }
    }
    // Функция для печати JSON-объекта без фигурных скобок
    private static void printJsonObject(JSONObject jsonObject){
        StringBuilder output=new StringBuilder();
        jsonObject.keys().forEachRemaining(key->{output.append(key).append(": ").append(jsonObject.get(key)).append(", \n");});
        System.out.println(output);
    }
}
