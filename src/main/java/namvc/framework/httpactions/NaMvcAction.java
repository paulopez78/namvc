package namvc.framework.httpactions;

import namvc.framework.httpcontext.MvcHttpResponse;
import namvc.framework.httpcontext.SessionInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class NaMvcAction
{
  public abstract void execute(MvcHttpResponse response) throws IOException;

  public void execute(MvcHttpResponse response, SessionInfo sessionInfo) throws IOException{
     setSession(response, sessionInfo);
     execute(response);
  }

  private void setSession(MvcHttpResponse response, SessionInfo sessionInfo) {
    List<String> values = new ArrayList<>();

    if(sessionInfo != null)
    {
      String cookieHeader = String.format("%s=%s;max-age=%s;version=1;Path=/;HttpOnly",
              SessionInfo.COOKIE_NAME,
              sessionInfo.getSessionId(),
              sessionInfo.getTimeout());

      values.add(cookieHeader);
      response.addHeader("Set-Cookie", values);
    }
  }
}