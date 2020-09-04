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
public class AuthorizationInterceptor implements ClientHttpRequestInterceptor{

    private static Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        log.info("[AuthorizationInterceptor]拦截器生效！");
        String token = "";
        try {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest accessTokenRequest = OAuthClientRequest
                .tokenLocation(CommonConstance.ACCESS_TOKEN_URI)
                .setGrantType(CommonConstance.GRANT_TYPE)
                .setClientId(CommonConstance.CLIENT_ID)
                .setClientSecret(CommonConstance.CLIENT_SECRET)
                .setScope(CommonConstance.SCOPE)
                .buildBodyMessage();

            OAuthJSONAccessTokenResponse accessToken = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
            token = accessToken.getAccessToken();
        } catch (Exception e) {
            log.error("[token]获取失败,错误信息：{}", e.getMessage());
        }

        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = request.getHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, String.join(" ","Bearer","eyJhbGciOiJSUzI1NiIsImtpZCI6Ijg2YThlOGE2YWQzOWI0YTcyMTk2NzMxMjliZDdhMzE0IiwidHlwIjoiSldUIn0.eyJuYmYiOjE1ODk5NDM0NDgsImV4cCI6MTU4OTk3MjI0OCwiaXNzIjoiaHR0cDovL2lkZW50aXR5LW15c3FsLmRldi5sdW56LmNuIiwiYXVkIjpbImh0dHA6Ly9pZGVudGl0eS1teXNxbC5kZXYubHVuei5jbi9yZXNvdXJjZXMiLCJ1Yy11c2Vycy1vdXRzaWRlLWFwaSJdLCJjbGllbnRfaWQiOiJ1Yy1jbGllbnQtdGVzdCIsInNjb3BlIjpbInVjLXVzZXJzLW91dHNpZGUtYXBpIl19.j7SAS1_-8jOiesMthnt9cSE6XHd_oYBPwKFCkdqFzXonKFcCeKRhby-w7EyM2D30XONQQ7Meby4LkdN8Ft1K4_SGPMNMdhOl81Hsn0qwKL54RqZvkNT0jWhz_-lTS2vxjuU3xQlIS7XDr8DkxEDuxb06EgC5NxfzMTCQlWsXf1hfgaCgFoNZnK_zxTeRD8lflPhFE1kNZiU-9UiDHdee9IM6Xy6xBnLTB9Tq6FPNNaHlBaXeSl7iM8X-hdJznyEjetwyljYVteyb82pcPGD7aoA-G-eJ4mhnHGAwv9_mCka5ugvebs68ZXG_NpYL8o8O4aOmJa-O06dn8l6wND-OKA"));
        return execution.execute(request, body);
    }
}
