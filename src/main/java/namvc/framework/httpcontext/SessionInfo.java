package namvc.framework.httpcontext;

public class SessionInfo
  {
    private String sessionId;
    private int timeout;
    public static final String COOKIE_NAME = "NAMVCSESSIONID";

    public SessionInfo(String sessionId, int timeout)
    {
      this.sessionId = sessionId;
      this.timeout = timeout;
    }

    public SessionInfo() {
      this("", 0);
    }

    public String getSessionId() {
      return sessionId;
    }

    public int getTimeout() {
      return timeout;
    }
  }