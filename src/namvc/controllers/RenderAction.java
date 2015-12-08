package namvc.controllers;

import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RenderAction extends NaMvcAction{
    private String html;

    public RenderAction(String html)
    {
      this.html = html;
    }

    @Override
    public void execute(HttpExchange t)
    {
      try
      {
        t.sendResponseHeaders(HttpURLConnection.HTTP_OK, html.length());
        OutputStream os = t.getResponseBody();
        os.write(html.getBytes());
        os.close();
      }
      catch(Exception ex)
      {

      }
    }
  }
