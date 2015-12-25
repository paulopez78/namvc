package namvc.framework.httpactions;

public class SetSessionInfo
  {
    private String sessionId;
    private int timeout;
    private String cookieName;

    public SetSessionInfo(String sessionId, String cookieName, int timeout)
    {
      this.sessionId = sessionId;
      this.timeout = timeout;
      this.cookieName  = cookieName;
    }

    public SetSessionInfo(String cookieName)
    {
      this.sessionId = "";
      this.timeout = 0;
      this.cookieName  = cookieName;
    }

    public String getSessionId() {
      return sessionId;
    }

    public int getTimeout() {
      return timeout;
    }

    public String getCookieName() {
      return cookieName;
    }
  }