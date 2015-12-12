package namvc.framework.httpactions;

import namvc.framework.httpcontext.NaMvcHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetSessionAction extends NaMvcAction
  {
    private String sessionId;
    private int timeout;
    private String cookieName;

    public SetSessionAction(String sessionId, String cookieName, int timeout)
    {
      this.sessionId = sessionId;
      this.timeout = timeout;
      this.cookieName  = cookieName;
    }

    @Override
    public void execute(NaMvcHttpResponse response)  throws IOException{
      List<String> values = new ArrayList<>();
      values.add(cookieName + "=" + sessionId + ";max-age=" + timeout + ";version=1;Path=/;HttpOnly");
      response.addHeader("Set-Cookie", values);
    }
  }