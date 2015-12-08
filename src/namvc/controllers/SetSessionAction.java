package namvc.controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import namvc.NaMvcPrincipal;

import java.util.ArrayList;
import java.util.List;

public class SetSessionAction extends RedirectAction
  {
    private String sessionId;
    private int timeout;

    public SetSessionAction(String sessionId, int timeout, String redirectPath)
    {
      super(redirectPath);
      this.sessionId = sessionId;
      this.timeout = timeout;
    }

    // http://cr.openjdk.java.net/~chegar/7095980/webrev.00/webrev/raw_files/new/test/sun/net/www/protocol/http/HttpOnly.java
    @Override
    public void execute(HttpExchange t)  {
      Headers respHeaders = t.getResponseHeaders();
      List<String> values = new ArrayList<>();
      values.add("NAMVCSESSIONID=" + sessionId + ";max-age=" + timeout + ";version=1;Path=/;HttpOnly");
      respHeaders.put("Set-Cookie", values);
      super.execute(t);
    }
  }