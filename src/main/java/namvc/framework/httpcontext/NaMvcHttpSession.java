package namvc.framework.httpcontext;

import namvc.framework.NaMvcPrincipal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NaMvcHttpSession {
    private final int timeout;
    private final Map<String, NaMvcPrincipal> sessions;
    private final String COOKIE_NAME = "NAMVCSESSIONID";

    public NaMvcHttpSession(int timeout)
    {
      this.timeout = timeout;
      this.sessions = new HashMap<>();
    }

    public String getCookieName(){
        return COOKIE_NAME;
    }

    public int getTimeout() { return this.timeout;}

    public NaMvcPrincipal getPrincipal(String sessionId) { return this.sessions.get(sessionId); }

    public String create(NaMvcPrincipal principal)
    {
      String sessionId = generateSessionId();
      sessions.put(sessionId, principal);
      return sessionId;
    }

    public void kill(String sessionId)
    {
      sessions.remove(sessionId);
    }

    private String generateSessionId()
    {
      return UUID.randomUUID().toString();
    }
}
