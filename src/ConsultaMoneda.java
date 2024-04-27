import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class ConsultaMoneda {
    public static CompletableFuture<Double> obtenerTasaDeConversion(String monedaDeseada, String monedaOrigen) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/8cd910033c06d715f026af78/latest/" + monedaOrigen))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> {
                    try {
                        // Parsear la respuesta JSON con Gson
                        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

                        // Obtener el objeto conversion_rates
                        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

                        // Obtener la tasa de conversión para la moneda deseada
                        Double tasaDeConversion = conversionRates.get(monedaDeseada).getAsDouble();

                        return tasaDeConversion;
                    }catch (JsonSyntaxException | NoSuchElementException | IllegalStateException e) {
                        throw new RuntimeException("Error al procesar la respuesta de la API: " + e.getMessage());
                    }
                })
                .exceptionally(ex -> {
                    if (ex.getCause() instanceof IOException) {
                        throw new RuntimeException("Error de conexión al consultar la API: " + ex.getMessage());
                    } else {
                        throw new RuntimeException("Error desconocido al consultar la API: " + ex.getMessage());
                    }
                });
    }
}
