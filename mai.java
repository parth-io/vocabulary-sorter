import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;
import java.time.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import java.net.http.*;
import java.net.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class mai {
    private static void makeAPICall(String word) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://books.google.com/ngrams/json?content=" + word + "&year_start=2018&year_end=2019&corpus=26&smoothing=0")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println);
    }
    
    private static void returnRequest(HttpClient client, URI target) {
        return client
            .sendAsync(
            HttpRequest.newBuilder().uri(target).GET().build(),
            HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> response.body());
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {
        // BufferedReader scan = new BufferedReader(new InputStreamReader(System.in), 131072);
        // System.out.println(makeAPICall("management"));
        List<URI> targets = Arrays.asList(
            new URI("https://postman-echo.com/get?foo1=bar1"),
            new URI("https://postman-echo.com/get?foo2=bar2"));
        HttpClient client = HttpClient.newHttpClient();
        List<CompletableFuture<String>> futures = targets.stream()
            .map(target -> CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                returnRequest(client, target);
            }))
            .collect(Collectors.toList());
        for (CompletableFuture<String> item : futures) {
            System.out.println(item.get());
        }
    }
}
