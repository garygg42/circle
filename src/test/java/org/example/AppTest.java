package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception{
        for (int i = 0; i < 20; i++) {
            try {
                RestClient client = RestClient.builder(HttpHost.create("127.0.0.1:9250")).build();
                Response put = client.performRequest(new Request("PUT", "/my_index"));
                Response get = client.performRequest(new Request("GET", "/my_index"));
                Response delete = client.performRequest(new Request("DELETE", "/my_index"));
                System.out.println(put);
                System.out.println(get);
                System.out.println(delete);
            } catch (Exception e) {
                System.out.println("Elastic failed");
            } finally {
                System.out.println("Main: " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}
