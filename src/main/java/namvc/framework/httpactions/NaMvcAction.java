package namvc.framework.httpactions;

import namvc.framework.httpcontext.NaMvcHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class NaMvcAction
{
  public abstract void execute(NaMvcHttpResponse response) throws IOException;

  public void execute(NaMvcHttpResponse response, SetSessionInfo sessionInfo) throws IOException {
     setSession(response, sessionInfo);
     execute(response);
  }

  private void setSession(NaMvcHttpResponse response, SetSessionInfo sessionInfo)
          throws IOException{
    List<String> values = new ArrayList<>();

    String cookieHeader = String.format("%s=%s;max-age=%s;version=1;Path=/;HttpOnly",
            sessionInfo.getCookieName(),
            sessionInfo.getSessionId(),
            sessionInfo.getTimeout());

    values.add(cookieHeader);
    response.addHeader("Set-Cookie", values);
  }
}