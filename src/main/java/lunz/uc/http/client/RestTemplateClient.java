package lunz.uc.http.client;

import lunz.uc.http.util.HttpUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: 客户端调用工具类
 * @Author: chenxiaojun
 * @CreateDate: 2019/12/6 13:16
 * @Version: 1.0
 */
@Component
public class RestTemplateClient {

    private static RestTemplate restTemplate;
    @Resource(name = "restTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        RestTemplateClient.restTemplate = restTemplate;
    }

    /**
     * post请求封装
     *
     * @param baseUrl 请求地址
     * @param bodyMap 请求体参数
     * @param headerMap 请求头参数
     * @param uriVariables 拼接在地址后面的参数
     * @return
     */
    public static String post(String baseUrl, Map<String, Object> bodyMap, Map<String, String> headerMap,Object... uriVariables) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(bodyMap, headerMap);
        return doExecute(baseUrl, HttpMethod.POST, httpEntity,uriVariables);
    }


    /**
     * get请求封装类
     *
     * @param baseUrl 请求地址
     * @param paramMap 请求参数
     * @param headerMap 请求头参数
     * @return
     */
    public static String get(String baseUrl, Map<String, Object> paramMap, Map<String, String> headerMap) {

        HttpEntity httpEntity = HttpUtils.getHttpEntity(null, headerMap);
        return doExecute(baseUrl, HttpMethod.GET, httpEntity, paramMap);


    }

    /**
     * delete请求封装类
     *
     * @param baseUrl 请求地址
     * @param paramMap 请求参数
     * @param headerMap 请求头参数
     * @return
     */
    public static String delete(String baseUrl, Map<String, Object> paramMap, Map<String, String> headerMap) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(null, headerMap);
        return doExecute(baseUrl, HttpMethod.DELETE, httpEntity, paramMap);
    }

    /**
     * patch请求封装类
     *
     * @param baseUrl 请求地址
     * @param bodyMap 请求体参数
     * @param headerMap 请求头参数
     * @param uriVariables 拼接在地址后面的参数
     * @return
     */
    public static String patch(String baseUrl, Map<String, Object> bodyMap, Map<String, String> headerMap,Object... uriVariables) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(bodyMap, headerMap);
        return doExecute(baseUrl, HttpMethod.PATCH, httpEntity,uriVariables);
    }

    /**
     * put请求封装类
     *
     * @param baseUrl 请求地址
     * @param bodyMap 请求体参数
     * @param headerMap 请求头参数
     * @param uriVariables 拼接在地址后面的参数
     * @return
     */
    public static String put(String baseUrl, Map<String, Object> bodyMap, Map<String, String> headerMap,Object... uriVariables) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(bodyMap, headerMap);
        return doExecute(baseUrl, HttpMethod.PUT, httpEntity,uriVariables);
    }

    /**
     * @Description: 统一执行入口
     */
    private static String doExecute(String baseUrl, HttpMethod httpMethod, HttpEntity httpEntity, Object... uriVariables) {
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, httpMethod, httpEntity, String.class, uriVariables);
        return response.getBody();
    }
}
