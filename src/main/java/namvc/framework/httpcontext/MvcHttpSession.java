package namvc.framework.httpcontext;

import namvc.framework.NaMvcPrincipal;

public interface MvcHttpSession {
    NaMvcPrincipal getPrincipal(String sessionId);

    String create(NaMvcPrincipal principal);

    void kill(String sessionId);

    SessionInfo getSessionInfo(String sessionId);
}
