package namvc.framework.httpcontext;

import namvc.framework.NaMvcPrincipal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NaMvcHttpSession implements MvcHttpSession{
    private final int timeout;
    private final Map<String, NaMvcPrincipal> sessions;

    public NaMvcHttpSession(int timeout)
    {
      this.timeout = timeout;
      this.sessions = new HashMap<>();
    }

    public NaMvcPrincipal getPrincipal(String sessionId) { return this.sessions.get(sessionId); }

    public String create(NaMvcPrincipal principal) {
        String sessionId = generateSessionId();
        sessions.put(sessionId, principal);
        return sessionId;
    }

    public SessionInfo getSessionInfo(String sessionId)
    {
        if (this.sessions.containsKey(sessionId))
        {
            return new SessionInfo(sessionId, timeout);
        }

        return this.createEmptySessionInfo();
    }

    public void kill(String sessionId)
    {
      sessions.remove(sessionId);
    }

    private String generateSessionId()
    {
      return UUID.randomUUID().toString();
    }

    private SessionInfo createEmptySessionInfo()
    {
        return new SessionInfo();
    }
}
