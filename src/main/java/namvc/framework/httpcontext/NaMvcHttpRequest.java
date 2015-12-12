package namvc.framework.httpcontext;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class NaMvcHttpRequest {

    private final NaMvcHttpParameters parameters;
    private HttpExchange exchange;

    public NaMvcHttpRequest(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
        this.parameters = new NaMvcHttpParameters(this.getQueryString(), this.getPostString());
    }

    public NaMvcHttpParameters getParameters()
    {
        return parameters;
    }

    public List<String> getHeader(String key)
    {
        return exchange.getRequestHeaders().get(key);
    }

    public String getPath()
    {
        return exchange.getRequestURI().getPath();
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public String getCookieValue(String cookieName)
    {
        // some small sanity check
        List<String> cookies  = this.getHeader("Cookie");
        String cookie = "";

        if (cookies != null)
        {
            cookie = cookies.stream()
                    .filter(item -> item.contains(cookieName))
                    .findFirst()
                    .get();

            return parseCookie(cookie);

        }

        return cookie;
    }

    private String parseCookie(String cookie)
    {
        String value = "";
        String[] cookiePart = cookie.split("=");
        if (cookiePart.length > 0)
        {
            value = cookiePart[1];
        }

        return value;
    }

    private String getQueryString()
    {
        URI requestedUri = exchange.getRequestURI();
        return requestedUri.getRawQuery();
    }

    private String getPostString() throws IOException {
        String query = "";

        if ("post".equalsIgnoreCase(this.getMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            query = br.readLine();

        }

        return query;
    }
}