package lunz.uc.http.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: 客户端工具类
 * @Author: chenxiaojun
 * @CreateDate: 2020/5/14 16:21
 * @Version: 1.0
*/
public class HttpUtils {

    /**
     * @Description: 构建body和header信息
     */
    public static HttpEntity getHttpEntity(Map<String, Object> params, Map<String, String> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        if (Objects.nonNull(headerMap)) {
            headerMap.forEach((key, val) -> {
                headers.add(key, val);
            });
        }
        return new HttpEntity<>(params, headers);
    }
}
