package lunz.uc.http.client;

import com.alibaba.fastjson.JSON;
import lunz.uc.http.util.HttpUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 默认客户端调用工具类（无需认证）
 * @Author: chenxiaojun
 * @CreateDate: 2019/12/6 13:16
 * @Version: 1.0
*/

@Component
public class RestTemplateDefaultClient{

    private static RestTemplate defaultRestTemplate;

    @Resource(name = "defaultRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        RestTemplateDefaultClient.defaultRestTemplate = restTemplate;
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
     * @param params 请求参数
     * @param headerMap 请求头参数
     * @return
     */
    public static String get(String baseUrl, Map<String, Object> params, Map<String, String> headerMap) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(null, headerMap);
        return doExecute(baseUrl, HttpMethod.GET, httpEntity, params);


    }

    /**
     * delete请求封装类
     *
     * @param baseUrl 请求地址
     * @param params 请求参数
     * @param headerMap 请求头参数
     * @return
     */
    public static String delete(String baseUrl, Map<String, Object> params, Map<String, String> headerMap) {
        HttpEntity httpEntity = HttpUtils.getHttpEntity(null, headerMap);
        return doExecute(baseUrl, HttpMethod.DELETE, httpEntity, params);
    }

    /**
     * patch请求封装
     *
     * @param baseUrl
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
     * put请求封装
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
        List<ClientHttpRequestInterceptor> interceptors = defaultRestTemplate.getInterceptors();
        System.out.println(JSON.toJSONString(interceptors));
        ResponseEntity<String> response = defaultRestTemplate.exchange(baseUrl, httpMethod, httpEntity, String.class, uriVariables);
        return response.getBody();
    }


}
