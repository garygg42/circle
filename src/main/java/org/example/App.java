package org.example;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

/**
 * Hello world!
 */
public class App {
    public void someMethod() throws Exception {
        for (int i = 0; i < 3; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Some method: " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            try {
                RestClient client = RestClient.builder(HttpHost.create("elastic-feature-test:9200")).build();
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
