package namvc.framework.httpcontext;

import java.io.IOException;
import java.util.List;

public interface MvcHttpResponse {
    void sendHttpCode(int httpCode, int length) throws IOException;

    void addHeader(String key, List<String> values);

    void write(String html) throws IOException;
}
