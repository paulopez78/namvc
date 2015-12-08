package namvc.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

  public class RedirectAction extends NaMvcAction{
    private String path;

    public RedirectAction(String path)
    {
      this.path = path;
    }

    @Override
    public void execute(HttpExchange t)
    {
      Headers respHeaders = t.getResponseHeaders();
      List<String> values = new ArrayList<>();
      values.add(this.path);
      respHeaders.put("Location", values);
      try {
        t.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_PERM, -1);
      } catch (IOException e) {
        e.printStackTrace();
      }
      t.close();
    }
  }
