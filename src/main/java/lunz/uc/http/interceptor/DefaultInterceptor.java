package lunz.uc.http.interceptor;

import lunz.uc.http.common.CommonConstance;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Description: 自定义token请求拦截器
 * @Author: chenxiaojun
 * @CreateDate: 2020/5/7 9:59
 * @Version: 1.0
*/
public class DefaultInterceptor implements ClientHttpRequestInterceptor{

    private static Logger log = LoggerFactory.getLogger(DefaultInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("[DefaultInterceptor]拦截器生效！");
        // 设置请求头
        HttpHeaders headers = request.getHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return execution.execute(request, body);
    }
}
