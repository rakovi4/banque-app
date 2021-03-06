import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;

import org.junit.Test;

public class BigTests {

    public  final String CUSTOMER_ID1 = UUID.randomUUID().toString();
    public final String CUSTOMER_URL_WITHDRAW =
            "http://localhost:8080/customers/" + CUSTOMER_ID1;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String WITHDRAW_99_URL = CUSTOMER_URL_WITHDRAW + "/withdraw?money=99";


    @Test
    public void testHelloEndpoint() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(getHttpRequest("http://localhost:8080/hello"), BodyHandlers.ofString());

        assertEquals("hello Marina!\n", response.body());
    }

    @Test
    public void getBalanceTest() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = getBalance(CUSTOMER_ID1);

        assertEquals("0",response.body());
    }

    private HttpResponse<String> getBalance(String customerId) throws IOException, InterruptedException, URISyntaxException {
        return httpClient.send(getHttpRequest("http://localhost:8080/customers/" + customerId + "/balance"),
                BodyHandlers.ofString());
    }


    private HttpRequest getHttpRequest(String str) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(str))
                .GET()
                .build();
    }
    
//    @Test
//    public void addAndWithdrawTest() throws IOException, URISyntaxException, InterruptedException {
//        addMoneyPost(CUSTOMER_ID1);
//        HttpResponse<String> httpResponse = httpClient.send(getHttpRequest(WITHDRAW_99_URL), BodyHandlers.ofString());
//        assertEquals(200, httpResponse.statusCode());
//        HttpResponse<String> response = getBalance(CUSTOMER_ID1);
//        assertEquals("1", response.body());
//
//    }

    private HttpResponse<String> addMoneyPost(String customer_id1) throws URISyntaxException, IOException, InterruptedException {
        return httpClient.send(HttpRequest.newBuilder()
                .uri(new URI(("http://localhost:8080/customers/" + CUSTOMER_ID1) + "/add"))
                .POST(HttpRequest.BodyPublishers.ofString("100"))
                .build(), BodyHandlers.ofString());
    }

    @Test
    public void addAndGetTestPost() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> responseAddMoneyPost = addMoneyPost(CUSTOMER_ID1);
        HttpResponse<String> response = getBalance(CUSTOMER_ID1);
        assertEquals(200, responseAddMoneyPost.statusCode());
        assertEquals("100",response.body());

        
    }

    @Test
    public void addAndWithdrawTestPost() throws IOException, URISyntaxException, InterruptedException {
        addMoneyPost(CUSTOMER_ID1);
        HttpResponse<String> httpResponsePost = WithdrawPost(CUSTOMER_ID1);
        assertEquals(200, httpResponsePost.statusCode());
        HttpResponse<String> response = getBalance(CUSTOMER_ID1);
        assertEquals("1", response.body());

    }

    public HttpResponse<String> WithdrawPost(String CUSTOMER_ID1) throws URISyntaxException, IOException, InterruptedException {
        return httpClient.send(HttpRequest.newBuilder()
                .uri(new URI(("http://localhost:8080/customers/" + CUSTOMER_ID1) + "/withdraw"))
                .POST(HttpRequest.BodyPublishers.ofString("99"))
                .build(), BodyHandlers.ofString());

    }

}
