package lunz.uc.http.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

/**
 * @Description: 自定义异常处理
 * @Author: chenxiaojun
 * @CreateDate: 2020/5/7 11:57
 * @Version: 1.0
*/
public class CustomErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 统一异常处理，响应状态不是2xx直接抛出异常信息
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HttpClientErrorException(response.getStatusCode(),response.getStatusText());
        }
    }
}
