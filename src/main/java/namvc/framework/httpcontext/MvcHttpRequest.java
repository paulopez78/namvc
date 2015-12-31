package namvc.framework.httpcontext;

import java.util.List;

public interface MvcHttpRequest {

    NaMvcHttpParameters getParameters();

    List<String> getHeader(String key);

    String getPath();

    String getMethod();

    String getCookieValue(String cookieName);
}
