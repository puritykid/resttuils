package lunz.uc.http.common;

import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 配置文件静态字段公共注入类
 * @Author: chenxiaojun
 * @CreateDate: 2020/5/7 9:53
 * @Version: 1.0
*/
@Component
public class CommonConstance {

    // 用户中心基础访问路径
    public static String BASE_URL;

    // 认证相关配置
    public static String CLIENT_ID;

    public static String CLIENT_SECRET;

    public static String ACCESS_TOKEN_URI;

    public static GrantType GRANT_TYPE;

    public static String SCOPE;

    @Value("${config.oauth2.common.clientId}")
    public void setClientId(String clientId) {
        CommonConstance.CLIENT_ID = clientId;
    }

    @Value("${config.oauth2.common.clientSecret}")
    public void setClientSecret(String clientSecret) {
        CommonConstance.CLIENT_SECRET = clientSecret;
    }

    @Value("${config.oauth2.common.accessTokenUri}")
    public void setAccessTokenUri(String accessTokenUri) {
        CommonConstance.ACCESS_TOKEN_URI = accessTokenUri;
    }

    @Value("${config.oauth2.common.client.grantType}")
    public void setGrantType(String grantType) {
        CommonConstance.GRANT_TYPE = GrantType.valueOf(grantType.toUpperCase());
    }

    @Value("${config.oauth2.common.scope}")
    public void setSCOPE(String SCOPE) {
        CommonConstance.SCOPE = SCOPE;
    }
}
