package namvc.framework.httpactions;

import namvc.framework.httpcontext.NaMvcHttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RenderAction extends NaMvcAction {
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
    public void execute(NaMvcHttpResponse response) throws IOException {
        response.sendHttpCode(this.httpCode, html.length());
        response.write(html);
    }
}
