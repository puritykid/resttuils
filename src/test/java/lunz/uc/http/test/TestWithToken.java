package lunz.uc.http.test;

import com.google.common.collect.Maps;
import lunz.uc.http.SpringBootClientTest;
import lunz.uc.http.client.RestTemplateClient;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestWithToken extends SpringBootClientTest {

    @Test
    public void get() {
        String url = "http://localhost:8006/api/v1/user/users/loginNameOrPhoneNo/info";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("queryString", "gaoying");
        String result = RestTemplateClient.get(url, paramMap,null);
        System.out.println(result);
    }

    @Test
    public void post() {
        String url = "http://localhost:8006/api/v1/user/users";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("beginDay", "2020-05-13 16:04:58");
        bodyMap.put("endDay", "2020-05-13 17:04:58");
        bodyMap.put("email", "123456@qq.com");
        bodyMap.put("idCard", "");
        bodyMap.put("loginName", "aaa001");
        bodyMap.put("name", "aaa001");
        bodyMap.put("shortName", "aaa001");
        bodyMap.put("tel","18354222887");
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("authToken","CF98B49A-9D83-4449-9047-2996E0A07570");
        String result = RestTemplateClient.post(url, bodyMap,headerMap);
        System.out.println(result);
    }

    @Test
    public void put() {
        String url = "http://localhost:8006/api/v1/user/users/disabled";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("loginName", "17743788629");
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("authToken","CF98B49A-9D83-4449-9047-2996E0A07570");
        headerMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String result = RestTemplateClient.put(url, paraMap,headerMap);
        System.out.println(result);
    }

    @Test
    public void delete() {
        String url = "http://localhost:8006/api/v1/user/users/000024DE-24D2-4A27-8D53-04507B282EE6";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("userId", Collections.singletonList("17743788629"));
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("authToken","CF98B49A-9D83-4449-9047-2996E0A07570");
        String result = RestTemplateClient.delete(url, paraMap,headerMap);
        System.out.println(result);
    }

    @Test
    public void postWithParam() {
        String url = "http://localhost:8006/api/v1/user/users/loginName/roles/0029F650-74BF-E511-80DE-AF2807B00613";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("loginName", "17743788629");
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headerMap.put("authToken","CF98B49A-9D83-4449-9047-2996E0A07570");
        String result = RestTemplateClient.post(url, null,headerMap,bodyMap);
        System.out.println(result);
    }

}
