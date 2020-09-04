package lunz.uc.http.config;

import lunz.uc.http.handler.CustomErrorHandler;
import lunz.uc.http.handler.CustomeUriTemplateHandler;
import lunz.uc.http.interceptor.AuthorizationInterceptor;
import lunz.uc.http.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @Description: 携带AuthToken统一配置类
 * @Author: chenxiaojun
 * @CreateDate: 2020/5/7 11:47
 * @Version: 1.0
*/
@Configuration
public class RestTemplateConfig extends AbstractRestTemplateConfig {

    /**
     * @Description: 默认模板
     */
    @Bean("defaultRestTemplate")
    public RestTemplate defaultRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setInterceptors(Collections.singletonList(new DefaultInterceptor()));
        restTemplate.setErrorHandler(new CustomErrorHandler());
        restTemplate.setUriTemplateHandler(new CustomeUriTemplateHandler());
        return restTemplate;
    }

    /**
     * @Description: 携带token的模板
     */
    @Bean("restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setInterceptors(Collections.singletonList(new AuthorizationInterceptor()));
        restTemplate.setErrorHandler(new CustomErrorHandler());
        restTemplate.setUriTemplateHandler(new CustomeUriTemplateHandler());
        return restTemplate;
    }
}