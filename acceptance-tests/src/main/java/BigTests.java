import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.Test;

public class BigTests {

    public static final String CUSTOMER_URL_GET =
            "http://localhost:8080/customers/8b081381-cabc-4471-9e00-f239cfbb7f3d";
    public static final String CUSTOMER_URL_ADD =
            "http://localhost:8080/customers/8b081381-cabc-4471-9e00-f239cfbb7f4d";
    public static final String CUSTOMER_URL_WITHDRAW =
            "http://localhost:8080/customers/8b081381-cabc-4471-9e00-f239cfbb7f5d";
    public static final String BALANCE_URL = CUSTOMER_URL_GET + "/balance";
    public static final String ADD_100_URL = CUSTOMER_URL_ADD + "/add?money=100";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String WITHDRAW_100_URL = CUSTOMER_URL_WITHDRAW + "withdraw?money=100";


    @Test
    public void testHelloEndpoint() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(getHttpRequest("http://localhost:8080/hello"), BodyHandlers.ofString());

        assertEquals("hello Marina!\n", response.body());
    }

    @Test
    public void getBalanceTest() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = getBalance();

        assertEquals("0",response.body());
    }

    private HttpResponse<String> getBalance() throws IOException, InterruptedException, URISyntaxException {
        return httpClient.send(getHttpRequest(BALANCE_URL),
                BodyHandlers.ofString());
    }

    private HttpRequest getHttpRequest(String str) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(str))
                .GET()
                .build();
    }

    @Test
    public void addAndGetTest() throws URISyntaxException, IOException, InterruptedException {
        addMoney();
        HttpResponse<String> response = getBalance();
        assertEquals("100",response.body());

    }

    private void addMoney() throws IOException, InterruptedException, URISyntaxException {
        httpClient.send(getHttpRequest(ADD_100_URL), BodyHandlers.ofString());
    }

    @Test
    public void addAndWithdrawTest() throws IOException, URISyntaxException, InterruptedException {
        addMoney();
        httpClient.send(getHttpRequest(WITHDRAW_100_URL), BodyHandlers.ofString());
        HttpResponse<String> response = getBalance();
        assertEquals("0", response.body());

    }


}
