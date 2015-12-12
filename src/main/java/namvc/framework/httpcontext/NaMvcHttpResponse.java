package namvc.framework.httpcontext;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class NaMvcHttpResponse {

    private HttpExchange exchange;
    public NaMvcHttpResponse(HttpExchange exchange)
    {
        this.exchange = exchange;
    }

    public void sendHttpCode(int httpCode, int length) throws IOException {
        exchange.sendResponseHeaders(httpCode, length);
    }

    public void addHeader(String key, List<String> values)
    {
        Headers respHeaders = exchange.getResponseHeaders();
        respHeaders.put(key, values);
    }

    public void write(String html) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
}
