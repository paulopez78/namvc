package namvc.framework.httpcontext;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaMvcHttpResponse {
    private final Map<String, List<String>> headers;
    private final HttpExchange exchange;
    public NaMvcHttpResponse(HttpExchange exchange)

    {
        this.exchange = exchange;
        this.headers = new HashMap<>();
    }

    public void sendHttpCode(int httpCode, int length) throws IOException {
        addResponseHeaders();
        exchange.sendResponseHeaders(httpCode, length);
    }

    public void addHeader(String key, List<String> values)
    {
        headers.put(key, values);
    }

    public void write(String html) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }

    private void addResponseHeaders()
    {
        Headers respHeaders = exchange.getResponseHeaders();
        for (Map.Entry<String,List<String>> entry : headers.entrySet())
            respHeaders.put(entry.getKey(), entry.getValue());
    }
}
