package lunz.uc.http.test;

import lunz.uc.http.SpringBootClientTest;
import lunz.uc.http.client.RestTemplateDefaultClient;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class TestDefault extends SpringBootClientTest {

    @Test
    public void get() {
        String url = "http://localhost:8006/api/v1/user/users/loginNameOrPhoneNo/info";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("queryString", "gaoying");
        String result = RestTemplateDefaultClient.get(url, paraMap,null);
        System.out.println(result);
    }


    @Test
    public void testPost() {
        String url = "http://localhost:8080/";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", "6545454");
        paramMap.put("name","sdfsdfsd");
        Map<String, String> headerMap = new HashMap<>();
        String result = RestTemplateDefaultClient.post(url, paramMap,headerMap);
        System.out.println(result);
    }

    @Test
    public void testPatch() {
        String url = "http://localhost:8080/";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", "6545454");
        paramMap.put("name","sdfsdfsd");
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String result = RestTemplateDefaultClient.patch(url, paramMap,headerMap,null);
        System.out.println(result);
    }
}
