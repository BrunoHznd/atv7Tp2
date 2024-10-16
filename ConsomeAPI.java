package fatecpg.Atividade.StreamLambda.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConsomeAPI {

    private static final String API_URL = "https://api.escuelajs.co/api/v1/products/";

    public static String requisicao() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // System.out.println("Código de resposta: " + response.statusCode());


        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        return response.body();

    }
}

