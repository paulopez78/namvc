package namvc.controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RenderAction extends NaMvcAction{
    private final int httpCode;
    private String html;

    public RenderAction(String html)
    {
        this(html, HttpURLConnection.HTTP_OK);
    }

    public RenderAction(String html, int httpCode)
    {
        this.html = html;
        this.httpCode = httpCode;
    }

    @Override
    public void execute(HttpExchange t) throws IOException {
        t.sendResponseHeaders(this.httpCode, html.length());
        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
}
