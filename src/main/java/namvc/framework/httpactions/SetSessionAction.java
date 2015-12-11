package namvc.framework.httpactions;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetSessionAction extends RedirectAction
  {
    private String sessionId;
    private int timeout;
    private String cookieName;

    public SetSessionAction(String sessionId, String cookieName, int timeout, String redirectPath)
    {
      super(redirectPath);
      this.sessionId = sessionId;
      this.timeout = timeout;
      this.cookieName  = cookieName;
    }

    // http://cr.openjdk.java.net/~chegar/7095980/webrev.00/webrev/raw_files/new/test/sun/net/www/protocol/http/HttpOnly.java
    @Override
    public void execute(HttpExchange t)  throws IOException{
      Headers respHeaders = t.getResponseHeaders();
      List<String> values = new ArrayList<>();
      values.add(cookieName + "=" + sessionId + ";max-age=" + timeout + ";version=1;Path=/;HttpOnly");
      respHeaders.put("Set-Cookie", values);
      super.execute(t);
    }
  }