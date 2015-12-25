package namvc.framework.httpmodules;

import namvc.framework.httpcontext.NaMvcHttpContext;
import namvc.framework.httpcontext.NaMvcHttpSession;
import namvc.framework.NaMvcPrincipal;
import namvc.framework.httpactions.NaMvcAction;
import namvc.framework.httpactions.RedirectAction;

import java.io.IOException;

public class NaMvcAuthenticationModule implements NaMvcModule {

    private final NaMvcHttpSession session;

    public NaMvcAuthenticationModule(NaMvcHttpSession session)
    {
        this.session = session;
    }

    public boolean execute(NaMvcHttpContext httpContext) throws IOException {
        String cookieName = session.getCookieName();
        String sessionId = httpContext.getRequest().getCookieValue(cookieName);
        if (sessionId.equals(""))
        {
            // redirect to login page with the current path /login?url=/page1
            NaMvcAction redirect = new RedirectAction
                    ("/login?redirectUrl="+ httpContext.getRequest().getPath());
            redirect.execute(httpContext.getResponse());
            return false;
        }
        else
        {
            NaMvcPrincipal currentPrincipal = session.getPrincipal(sessionId);
            httpContext.setPrincipal(currentPrincipal);
            httpContext.setSessionId(sessionId);
            return true;
        }
    }
}
