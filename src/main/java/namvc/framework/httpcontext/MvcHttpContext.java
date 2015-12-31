package namvc.framework.httpcontext;

import namvc.framework.NaMvcPrincipal;

public interface MvcHttpContext {

    MvcHttpRequest getRequest();

    MvcHttpResponse getResponse();

    NaMvcPrincipal getPrincipal();

    SessionInfo getSessionInfo();

    void login(NaMvcPrincipal currentPrincipal);

    void logout();

    boolean authenticated();
}
