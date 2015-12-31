package namvc.framework.httpcontext;

import com.sun.net.httpserver.HttpExchange;
import namvc.framework.NaMvcPrincipal;

public class NaMvcHttpContext implements MvcHttpContext{
  private final MvcHttpSession session;
  private final MvcHttpRequest request;
  private final MvcHttpResponse response;
  private String sessionId;

  public NaMvcHttpContext(HttpExchange exchange, MvcHttpSession session) {
    this.request = new NaMvcHttpRequest(exchange);
    this.response = new NaMvcHttpResponse(exchange);
    this.session = session;
    this.sessionId = "";
  }

  public MvcHttpRequest getRequest()
  {
    return this.request;
  }

  public MvcHttpResponse getResponse()
  {
    return this.response;
  }

  public NaMvcPrincipal getPrincipal()
  {
    return this.session.getPrincipal(getSessionId());
  }

  public SessionInfo getSessionInfo(){
    return this.session.getSessionInfo(getSessionId());
  }

  public void login(NaMvcPrincipal currentPrincipal) {
    this.sessionId = session.create(currentPrincipal);
  }

  public void logout() {
    session.kill(getSessionId());
  }

  public boolean authenticated() {
    return !getSessionId().equals("");
  }

  private String getSessionId()
  {
    if (sessionId.equals(""))
    {
      return this.request.getCookieValue(SessionInfo.COOKIE_NAME);
    }
    else
    {
      return this.sessionId;
    }
  }
}