package main.java.namvc.framework;

import java.util.Map;

public class NaMvcHttpContext {
  private final NaMvcPrincipal principal;
  private final String path;
  private final Map<String,Object> params;
  private final String sessionId;

  public NaMvcHttpContext(NaMvcPrincipal principal, String path, Map<String,Object> params, String sessionId)
  {
    this.principal = principal;
    this.path = path;
    this.params = params;
    this.sessionId = sessionId;
  }

  public String getPath(){
    return this.path;
  }

  public NaMvcPrincipal getPrincipal()
  {
    return this.principal;
  }

  public Map<String,Object> getParameters()
  {
    return this.params;
  }

  public String getSessionId() {
    return sessionId;
  }
}