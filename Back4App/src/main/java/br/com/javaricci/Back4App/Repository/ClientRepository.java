package br.com.javaricci.Back4App.Repository;

import br.com.javaricci.Back4App.Model.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {

    private static final String API_URL = "https://parseapi.back4app.com/parse/classes/Cliente/";
    private static final String APP_ID = "Informar Seu ID";
    private static final String API_KEY = "Informar Sua Chave";

    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("X-Parse-Application-Id", APP_ID)
                    .header("X-Parse-REST-API-Key", API_KEY)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(response.body());
                JSONArray results = (JSONArray) jsonObject.get("results");

                for (Object obj : results) {
                    JSONObject clientJson = (JSONObject) obj;
                    Client client = new Client(
                            (String) clientJson.get("objectId"),
                            (String) clientJson.get("cliName"),
                            Double.parseDouble((String) clientJson.get("cliPreco").toString()),
                            ((Long) clientJson.get("cliEstoque")).intValue(),
                            (Boolean) clientJson.get("estaVendendo")
                    );
                    clients.add(client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client findClientById(String id) {
        Client client = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + id))
                    .header("X-Parse-Application-Id", APP_ID)
                    .header("X-Parse-REST-API-Key", API_KEY)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONParser parser = new JSONParser();
                JSONObject clientJson = (JSONObject) parser.parse(response.body());

                client = new Client(
                        (String) clientJson.get("objectId"),
                        (String) clientJson.get("cliName"),
                        (Double) clientJson.get("cliPreco"),
                        ((Long) clientJson.get("cliEstoque")).intValue(),
                        (Boolean) clientJson.get("estaVendendo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public void addClient(Client client) {
        try {
            JSONObject json = new JSONObject();
            json.put("cliName", client.getName());
            json.put("cliPreco", client.getPrice());
            json.put("cliEstoque", client.getStock());
            json.put("estaVendendo", client.isSelling());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("X-Parse-Application-Id", APP_ID)
                    .header("X-Parse-REST-API-Key", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toJSONString()))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        try {
            JSONObject json = new JSONObject();
            json.put("cliName", client.getName());
            json.put("cliPreco", client.getPrice());
            json.put("cliEstoque", client.getStock());
            json.put("estaVendendo", client.isSelling());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + client.getId()))
                    .header("X-Parse-Application-Id", APP_ID)
                    .header("X-Parse-REST-API-Key", API_KEY)
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json.toJSONString()))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + id))
                    .header("X-Parse-Application-Id", APP_ID)
                    .header("X-Parse-REST-API-Key", API_KEY)
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
