package lunz.uc.http.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义get请求处理模板
 * @author haha
 */
@Component
public class CustomeUriTemplateHandler implements UriTemplateHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uriTemplate);
        if (uriVariables == null) {
            return uriComponentsBuilder.build().encode().toUri();
        }
        uriVariables.forEach((key,val)->{
            if (Objects.nonNull(val)) {
                if (val instanceof List) {
                    List vals = (List) val;
                    vals.stream().forEach(o -> {
                        uriComponentsBuilder.queryParam(key, o);
                    });
                } else if (val instanceof Object[]) {
                    Object[] vals = (Object[]) val;
                    Arrays.stream(vals).forEach(o -> {
                        uriComponentsBuilder.queryParam(key, o);
                    });
                }else if(val instanceof Map){
                    Map vals = (Map) val;
                    vals.forEach((k, v) -> {
                        if (v != null) {
                            uriComponentsBuilder.queryParam(key + "." + k, v);
                        }
                    });
                }else {
                    uriComponentsBuilder.queryParam(key,val);
                }
            }
        });
        uriTemplate = uriComponentsBuilder.build().toUriString();
        return URI.create(uriTemplate);
    }

    /**
     * @Description: 获取传入参数并调用extend扩展方法
     */
    @Override
    public URI expand(String uriTemplate, Object... uriVariables) {
        if (uriVariables.length != 0 && uriVariables[0] != null) {
            Map map = (Map)uriVariables[0];
            return expand(uriTemplate, map);
        } else {
            return URI.create(uriTemplate);
        }
    }
}
