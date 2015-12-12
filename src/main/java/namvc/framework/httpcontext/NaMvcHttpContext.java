package namvc.framework.httpcontext;

import com.sun.net.httpserver.HttpExchange;
import namvc.framework.NaMvcPrincipal;

import java.io.IOException;

public class NaMvcHttpContext {
  private NaMvcPrincipal principal;
  private String sessionId;

  private NaMvcHttpRequest request;
  private NaMvcHttpResponse response;

  public NaMvcHttpContext(HttpExchange exchange) throws IOException {
    this.request = new NaMvcHttpRequest(exchange);
    this.response = new NaMvcHttpResponse(exchange);
    this.principal = (NaMvcPrincipal) exchange.getAttribute("principal");
    this.sessionId = (String) exchange.getAttribute("sessionId");
  }

  public NaMvcHttpRequest getRequest()
  {
    return this.request;
  }

  public NaMvcHttpResponse getResponse()
  {
    return this.response;
  }

  public NaMvcPrincipal getPrincipal()
  {
    return this.principal;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setPrincipal(NaMvcPrincipal principal) {
    this.principal = principal;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}