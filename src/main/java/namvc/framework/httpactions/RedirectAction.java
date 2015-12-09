package main.java.namvc.framework.httpactions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class RedirectAction extends NaMvcAction {
    private String path;

    public RedirectAction(String path)
    {
      this.path = path;
    }

    @Override
    public void execute(HttpExchange t) throws IOException
    {
      Headers respHeaders = t.getResponseHeaders();
      List<String> values = new ArrayList<>();
      values.add(this.path);
      respHeaders.put("Location", values);
      t.sendResponseHeaders(HttpURLConnection.HTTP_MOVED_PERM, -1);
      t.close();
    }
  }
